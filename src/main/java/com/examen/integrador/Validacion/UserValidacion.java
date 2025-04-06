package com.examen.integrador.Validacion;

 
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserValidacion {

    private final UserRepositorio userRepositorio;

    @Autowired
    public UserValidacion(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;

    }

    public Map<String, Object> validarUsuarios(Usuarios usu) {

        String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";
        Map<String, Object> respuestaValidacion = new HashMap<>();
        List<String> mensajeValidacion = new ArrayList<>();

        try {
            if (usu == null) {
                mensajeValidacion.add("Usuario no puede ser nulo");
            } else {
                if (usu.getNombre() == null || !usu.getNombre().matches(regexNombre)) {
                    mensajeValidacion.add("Nombre no puede estar vacío y solo puede contener letras.");
                }

                if (userRepositorio.existsByUsername(usu.getUsername())) {
                    mensajeValidacion.add("Username ya existe");
                }

                if (usu.getApellido() == null || !usu.getApellido().matches(regexNombre)) {
                    mensajeValidacion.add("Apellido no puede estar vacío y solo puede contener letras.");
                }

                if (usu.getPassword() == null || usu.getPassword().length() > 15) {
                    mensajeValidacion.add("Contraseña no puede estar vacía ni superar los 15 caracteres.");

                    if (usu.getConfirm_password() == null || usu.getConfirm_password().length() > 15) {
                        mensajeValidacion
                                .add("Confirmación de contraseña no puede estar vacía ni superar los 15 caracteres.");

                    }
                }

                if (usu.getPassword() != null && usu.getConfirm_password() != null &&
                        !usu.getPassword().equals(usu.getConfirm_password())) {
                    mensajeValidacion.add("Las contraseñas deben ser iguales.");
                }

                if (usu.getEmail() == null || !usu.getEmail().matches(regexEmail)) {
                    mensajeValidacion.add("El email no puede estar vacío y debe cumplir con el formato.");
                }

                if (usu.getNacimiento() == null) {
                    mensajeValidacion.add("Fecha de nacimiento no puede estar vacía y debe ser en formato dd-MM-yyyy.");
                }
            }

            if (mensajeValidacion.isEmpty()) {
                respuestaValidacion.put("Confirmación", "Informacion registrada correctamente , usuario registrado");
            } else {
                respuestaValidacion.put("Error", mensajeValidacion);
            }

        } catch (Exception e) {
            respuestaValidacion.put("ErrorTry", e.getMessage());
        }

        return respuestaValidacion;
    }

}
