package com.examen.integrador.Validacion;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserValidacion {



    public Map<String,Object> validarUsuarios(Usuarios usu) {

        String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";
        Map<String, Object> respuesta = new HashMap<>();
        List<String> mensaje = new ArrayList<>();

        try {
            if (usu == null) {
                mensaje.add("Usuario no puede ser nulo");
            } else {
                if (usu.getNombre() == null || !usu.getNombre().matches(regexNombre)) {
                    mensaje.add("Nombre no puede estar vacío y solo puede contener letras.");
                }

                if (usu.getApellido() == null || !usu.getApellido().matches(regexNombre)) {
                    mensaje.add("Apellido no puede estar vacío y solo puede contener letras.");
                }

                if (usu.getPassword() == null || usu.getPassword().length() > 15) {
                    mensaje.add("Contraseña no puede estar vacía ni superar los 15 caracteres.");
                }

                if (usu.getConfirm_password() == null || usu.getConfirm_password().length() > 15) {
                    mensaje.add("Confirmación de contraseña no puede estar vacía ni superar los 15 caracteres.");
                }

                if (usu.getPassword() != null && usu.getConfirm_password() != null &&
                        !usu.getPassword().equals(usu.getConfirm_password())) {
                    mensaje.add("Las contraseñas deben ser iguales.");
                }

                if (usu.getEmail() == null || !usu.getEmail().matches(regexEmail)) {
                    mensaje.add("El email no puede estar vacío y debe cumplir con el formato.");
                }

                if (usu.getNacimiento() == null) {
                    mensaje.add("Fecha de nacimiento no puede estar vacía y debe ser en formato dd-MM-yyyy.");
                }
            }


            if (mensaje.isEmpty()) {
                respuesta.put("Confirmación", "Usuario validado");
            } else {
                respuesta.put("Error", mensaje);
            }

        } catch (Exception e) {
            respuesta.put("Error try-catch", e.getMessage());
        }

        System.out.println("Mensaje después de validaciones: " + respuesta);

        return respuesta;
    }









}
