package com.examen.integrador.Servicios;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import com.examen.integrador.Validacion.UserValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSerivicio {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositorio userRepositorio;
    private final UserValidacion userValidacion;

    /*

    Listar por Rol
    Eliminar
    Editar
    Usuario logeado
    */


    @Autowired
    public UserSerivicio(PasswordEncoder passwordEncoder, UserRepositorio userRepositorio, UserValidacion userValidacion, TokenServicio tokenServicio) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositorio = userRepositorio;
        this.userValidacion = userValidacion;
    }

    public Map<String,Object> registrarUsuario(Usuarios usuarios){

        Map<String,Object> respuestaRegistro = new HashMap<>();

        try{

            
            Map<String,Object> resultadoValidacion =userValidacion.validarUsuarios(usuarios);

            if(resultadoValidacion.containsKey("Confirmación")){

                usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));

                usuarios.setConfirm_password(passwordEncoder.encode(usuarios.getPassword()));

                usuarios.setId(AutogenerarId());

                if(usuarios.getRoles() == null){
                    usuarios.setRoles(RolesEnum.ALUMNO);
                }

                System.out.println(usuarios.getPassword() + " " + usuarios.getConfirm_password());

                userRepositorio.save(usuarios);

                respuestaRegistro.put("Confirmación" , resultadoValidacion.get("Confirmación"));


            } else {
                respuestaRegistro.put("Error" ,resultadoValidacion.get("Error"));
             }

        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();

            respuestaRegistro.put("ErrorTry", e.getMessage());
            respuestaRegistro.put("detalleTry", stackTrace); //
        }

        return respuestaRegistro;

    }

    public Map<String,Object> listarTodosUsuarios( ){

        Map<String,Object> resultado = new HashMap<>();

        try{
            List<Usuarios> usuariosList = userRepositorio.findAll();

            if(usuariosList.isEmpty()){
                resultado.put("mensaje" , "No hay usuarios registrados");
            } else {
                resultado.put("mensaje" , usuariosList);
            }

        }catch (Exception e){

            String mensaje = e.getMessage();
            resultado.put("mensaje" , mensaje);

        }

        return resultado;

    }

    public Map<String,Object> listarAdmins() {

        Map<String, Object> resultado = new HashMap<>();
        List<Usuarios> usuariosAdmins;

        try{
            usuariosAdmins = userRepositorio.findUsuariosByRoles(RolesEnum.ADMIN);

            if(usuariosAdmins.isEmpty()){
                 resultado.put("mensaje" , "No hay administradores registrados");
            }else{
                resultado.put("mensaje" ,"Administradores encontrados");
                resultado.put("Total" , usuariosAdmins.size());
                resultado.put("Admins" , usuariosAdmins);
             }

        }catch (Exception e){
            resultado.put("mensaje" ,e.getMessage());
        }

            return resultado;
    }

    public Map<String,Object> listarAlumnos() {

        Map<String, Object> resultado = new HashMap<>();

        List<Usuarios> usuariosAlumnos;

        try{
            usuariosAlumnos = userRepositorio.findUsuariosByRoles(RolesEnum.ALUMNO);

            if(usuariosAlumnos.isEmpty()){
                resultado.put("mensaje" , "No hay administradores registrados");
            }else{
                resultado.put("mensaje" ,"Administradores encontrados");
                resultado.put("Total" , usuariosAlumnos.size());
                resultado.put("Admins" , usuariosAlumnos);
            }

        }catch (Exception e){
            resultado.put("mensaje" ,e.getMessage());
        }

        return resultado;
    }






















    public String AutogenerarId(){

        StringBuilder inicio = new StringBuilder("USR-");
        String valores = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String[] arrayCaracteres = valores.split("");

        int min = 0;
        int max = 35;
        int numeroAleatorio;

        for(int i = 0 ; i<3 ; i++){
            numeroAleatorio = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
            inicio.append(arrayCaracteres[numeroAleatorio]);
        }
        return inicio.toString();
    }



}
