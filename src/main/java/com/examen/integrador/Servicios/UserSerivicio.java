package com.examen.integrador.Servicios;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import com.examen.integrador.Validacion.UserValidacion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@Service
public class UserSerivicio {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositorio userRepositorio;
    private final UserValidacion userValidacion;

    /*
     * 
     * Editar
     * 
     * Usuario logeado
     * 
     */

    @Autowired
    public UserSerivicio(PasswordEncoder passwordEncoder, UserRepositorio userRepositorio,
            UserValidacion userValidacion, TokenServicio tokenServicio) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositorio = userRepositorio;
        this.userValidacion = userValidacion;
    }
 
    public Map<String, Object> registrarUsuario(Usuarios usuarios) {

        Map<String, Object> respuestaRegistro = new HashMap<>();

        try {

            Map<String, Object> resultadoValidacion = userValidacion.validarUsuarios(usuarios);

            if (resultadoValidacion.containsKey("Confirmación")) {

                usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));

                usuarios.setConfirm_password(passwordEncoder.encode(usuarios.getPassword()));

                usuarios.setId(AutogenerarId(usuarios.getRoles().name()));

                if (usuarios.getRoles() == null) {
                    usuarios.setRoles(RolesEnum.ALUMNO);
                }

                userRepositorio.save(usuarios);

                respuestaRegistro.put("Confirmación", resultadoValidacion.get("Confirmación"));

            } else {
                respuestaRegistro.put("Error", resultadoValidacion.get("Error"));
            }

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();

            respuestaRegistro.put("ErrorTry", e.getMessage());
            respuestaRegistro.put("detalleTry", stackTrace); //
        }

        return respuestaRegistro;

    }

    public Map<String, Object> listarTodosUsuarios() {

        Map<String, Object> resultado = new HashMap<>();

        try {
            List<Usuarios> usuariosList = userRepositorio.findAll();

            if (usuariosList.isEmpty()) {
                resultado.put("mensaje", "No hay usuarios registrados");
            } else {
                resultado.put("mensaje", usuariosList);
            }

        } catch (Exception e) {

            String mensaje = e.getMessage();
            resultado.put("mensaje", mensaje);

        }

        return resultado;

    }

    public Map<String, Object> listarAdmins() {

        Map<String, Object> resultado = new HashMap<>();
        List<Usuarios> usuariosAdmins;

        try {
            usuariosAdmins = userRepositorio.findUsuariosByRoles(RolesEnum.ADMIN);

            if (usuariosAdmins.isEmpty()) {
                resultado.put("mensaje", "No hay administradores registrados");
            } else {
                resultado.put("mensaje", "Administradores encontrados");
                resultado.put("Total", usuariosAdmins.size());
                resultado.put("Admins", usuariosAdmins);
            }

        } catch (Exception e) {
            resultado.put("mensaje", e.getMessage());
        }

        return resultado;
    }

    public Map<String, Object> listarAlumnos() {

        Map<String, Object> resultado = new HashMap<>();

        List<Usuarios> usuariosAlumnos;

        try {
            usuariosAlumnos = userRepositorio.findUsuariosByRoles(RolesEnum.ALUMNO);

            if (usuariosAlumnos.isEmpty()) {
                resultado.put("mensaje", "No hay Alumnos registrados");
            } else {
                resultado.put("mensaje", "Alumnos encontrados");
                resultado.put("Total", usuariosAlumnos.size());
                resultado.put("Alumnos", usuariosAlumnos);
            }

        } catch (Exception e) {
            resultado.put("mensaje", e.getMessage());
        }

        return resultado;
    }

    public Map<String, Object> listarProfesores() {

        Map<String, Object> resultado = new HashMap<>();

        List<Usuarios> usuariosProfesores;

        try {
            usuariosProfesores = userRepositorio.findUsuariosByRoles(RolesEnum.PROFESOR);

            if (usuariosProfesores.isEmpty()) {
                resultado.put("mensaje", "No hay Profesores registrados");
            } else {
                resultado.put("mensaje", "Profesores encontrados");
                resultado.put("Total", usuariosProfesores.size());
                resultado.put("Profesores", usuariosProfesores);
            }

        } catch (Exception e) {
            resultado.put("mensaje", e.getMessage());
        }

        return resultado;
    }

    public Map<String, Object> eliminarUsuario(String id) {

        Map<String, Object> resultado = new HashMap<>();

        try {
            Optional<Usuarios> usuariosOptional = userRepositorio.findById(id);

            if (usuariosOptional.isEmpty()) {
                throw new EntityNotFoundException("No se encontro usuario con ese ID");
            } else {

                Usuarios usuarioDelete = usuariosOptional.get();
                userRepositorio.delete(usuarioDelete);
                resultado.put("mensaje", "Usuario eliminado");
                resultado.put("username eliminado: ", usuarioDelete.getUsername());
            }

        } catch (Exception e) {
            resultado.put("error", e.getMessage());
        }

        return resultado;
    }

    public Map<String, Object> editarUsuario(Usuarios usuarios, String Id) {

        Map<String, Object> respuestaUpdate = new HashMap<>();
        Map<String, Object> respuestaValidacion;

        try {

            Optional<Usuarios> usuariosOptional = userRepositorio.findById(Id);

            if (usuariosOptional.isEmpty()) {
                throw new EntityNotFoundException("usuario no encontrado");
            }

            Usuarios usuariosById = usuariosOptional.get();

            respuestaValidacion = userValidacion.validarUsuarios(usuarios);

            if (respuestaValidacion.containsKey("Confirmación")) {

                usuariosById.setUsername(usuarios.getUsername());
                usuariosById.setNombre(usuarios.getNombre());
                usuariosById.setApellido(usuarios.getApellido());
                usuariosById.setEmail(usuarios.getEmail());
                usuariosById.setPassword(usuarios.getPassword());
                usuariosById.setConfirm_password(usuarios.getConfirm_password());
                usuariosById.setRoles(usuarios.getRoles());
                usuariosById.setNacimiento(usuarios.getNacimiento());
                usuariosById.setUsername(usuarios.getUsername());

                userRepositorio.save(usuariosById);

                respuestaUpdate.put("confirmacion: ", "Usuario actualizado con excito");

            } else if (respuestaValidacion.containsKey("Error")) {

                respuestaUpdate.put("error: ", respuestaValidacion.get("Error"));

            } else if (respuestaValidacion.containsKey("ErrorTry")) {
                respuestaUpdate.put("error try: ", respuestaValidacion.get("ErrorTry"));
            }

        } catch (Exception e) {
            respuestaUpdate.put("error", e.getMessage());
        }

        return respuestaUpdate;

    }

    public String AutogenerarId(String role) {

        StringBuilder inicio = new StringBuilder("USR-");

        switch (role) {
            case "ADMIN":

                inicio = new StringBuilder("ADM-");
                break;

            case "PROFESOR":

                inicio = new StringBuilder("PRF-");
                break;

            default:

                inicio = new StringBuilder("ALM-");
                break;
        }

        String valores = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String[] arrayCaracteres = valores.split("");

        int min = 0;
        int max = 35;
        int numeroAleatorio;

        for (int i = 0; i < 3; i++) {
            numeroAleatorio = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
            inicio.append(arrayCaracteres[numeroAleatorio]);
        }
        return inicio.toString();
    }

}
