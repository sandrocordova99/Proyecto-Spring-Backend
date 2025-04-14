package com.examen.integrador.Validacion;

import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.Entidades.Alumnos;
 import com.examen.integrador.Mapper.AlumnoMapper;
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
    
    String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
    String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";

    public Map<String, Object> validarUsuarios(RequestAlumnoDTO usu) {

        List<String> respuestaValidacionComun = validacionComun(usu);

        List<String> respuestaValidacionEntidades = new ArrayList();

        List<String> erroresTotales = new ArrayList<>();

        Map<String, Object> respuesta = new HashMap();

        switch (usu.getRoles().name().toString()) {
            case "ADMIN":
                respuestaValidacionEntidades = validarAdmin(usu);
                break;

            case "ALUMNO":
                respuestaValidacionEntidades = validarAlumno(usu);
                break;

            default:
                respuestaValidacionEntidades = validarProfe(usu);
                break;
        }

        erroresTotales.addAll(respuestaValidacionComun);

        erroresTotales.addAll(respuestaValidacionEntidades);

        if (erroresTotales.isEmpty()) {
            respuesta.put("Confirmación", "Información registrada correctamente.");
        } else {
            respuesta.put("Errores", erroresTotales);
        }
    
        return respuesta;
         

    }

    private List<String> validacionComun(RequestAlumnoDTO usu) {

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

            

        } catch (Exception e) {
            mensajeValidacion.add(e.getMessage());
        }

        return mensajeValidacion;
    }

    private List<String> validarAlumno(RequestAlumnoDTO usu) {

        Alumnos alu = AlumnoMapper.instancia.toAlumnoRequest(usu);

        List<String> mensajeValidacion = new ArrayList<>();

        if (alu.getNombreDeApoderado().isEmpty() || !alu.getNombreDeApoderado().matches(regexNombre)) {
            mensajeValidacion.add("Nombre no puede estar vacio ni contener caracteres invalidos");
        }

        return mensajeValidacion;
    }

    private List<String> validarProfe(RequestAlumnoDTO usu) {

        throw new UnsupportedOperationException("Unimplemented method 'validarProfe'");
    }

    private List<String> validarAdmin(RequestAlumnoDTO usu) {

        throw new UnsupportedOperationException("Unimplemented method 'validarAdmin'");
    }

}