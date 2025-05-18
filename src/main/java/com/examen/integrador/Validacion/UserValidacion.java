package com.examen.integrador.Validacion;

import com.examen.integrador.DTO.UsuarioBaseDTO;
import com.examen.integrador.DTO.AlumnoDTO.AlumnoEditDTO;
import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserValidacion {

    private final UserRepositorio userRepositorio;

    String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
    String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";

    @Autowired
    public UserValidacion(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;

    }

    public Map<String, Object> validarUsuarios(UsuarioBaseDTO usu) {

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

    private List<String> validacionComun(UsuarioBaseDTO usu) {
        List<String> mensajeValidacion = new ArrayList<>();

        if (usu == null) {
            mensajeValidacion.add("Usuario no puede ser nulo");
            return mensajeValidacion;
        }

        if (usu.getNombre() == null || !usu.getNombre().matches(regexNombre)) {
            mensajeValidacion.add("Nombre no puede estar vacío y solo puede contener letras.");
        }

        if (userRepositorio.existsByUsername(usu.getUsername())) {
            mensajeValidacion.add("Username ya existe");
        }

        if (usu.getApellido() == null || !usu.getApellido().matches(regexNombre)) {
            mensajeValidacion.add("Apellido no puede estar vacío y solo puede contener letras.");
        }

        // ⚠️ Solo si no es edición, validá contraseña y nacimiento
        if (!usu.isEdicion()) {
            if (usu.getPassword() == null || usu.getPassword().length() > 15) {
                mensajeValidacion.add("Contraseña no puede estar vacía ni superar los 15 caracteres.");
            }

            if (usu.getConfirm_password() == null || usu.getConfirm_password().length() > 15) {
                mensajeValidacion.add("Confirmación de contraseña no puede estar vacía ni superar los 15 caracteres.");
            }

            if (usu.getPassword() != null && usu.getConfirm_password() != null &&
                    !usu.getPassword().equals(usu.getConfirm_password())) {
                mensajeValidacion.add("Las contraseñas deben ser iguales.");
            }

            if (usu.getNacimiento() == null) {
                mensajeValidacion
                        .add("Fecha de nacimiento no puede estar vacía y debe ser en formato dd-MM-yyyy.");
            }

            if (usu.getRoles() == null) {
                mensajeValidacion.add("Rol no puede ser nulo.");
            }
        }

        if (usu.getEmail() == null || !usu.getEmail().matches(regexEmail)) {
            mensajeValidacion.add("El email no puede estar vacío y debe cumplir con el formato.");
        }

        return mensajeValidacion;
    }

    private List<String> validarAlumno(UsuarioBaseDTO usu) {
        List<String> mensajeValidacion = new ArrayList<>();

        if (usu instanceof RequestAlumnoDTO alumnoDTO) {

            if (alumnoDTO.getNombreDeApoderado() == null || alumnoDTO.getNombreDeApoderado().isEmpty()
                    || !alumnoDTO.getNombreDeApoderado().matches(regexNombre)) {
                mensajeValidacion.add("El nombre del apoderado no puede estar vacío ni contener caracteres inválidos.");
            }   
            
        } else if (usu instanceof AlumnoEditDTO alumnoEditDTO) {

            if (alumnoEditDTO.getNombreDeApoderado() == null || alumnoEditDTO.getNombreDeApoderado().isEmpty()
                    || !alumnoEditDTO.getNombreDeApoderado().matches(regexNombre)) {
                mensajeValidacion.add("El nombre del apoderado no puede estar vacío ni contener caracteres inválidos.");
            }

        } else {
            mensajeValidacion.add("Error interno: El DTO recibido no es de tipo alumno.");
        }

        return mensajeValidacion;
    }

    private List<String> validarProfe(UsuarioBaseDTO usu) {

        throw new UnsupportedOperationException("Unimplemented method 'validarProfe'");
    }

    private List<String> validarAdmin(UsuarioBaseDTO usu) {

        throw new UnsupportedOperationException("Unimplemented method 'validarAdmin'");
    }

}