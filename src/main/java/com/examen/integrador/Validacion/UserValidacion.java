package com.examen.integrador.Validacion;

import com.examen.integrador.Entidades.RolesEnum;
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

    public  Map<String,Object> validarUsuarios(Usuarios usu){

        String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\\\s]+$";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";
        Map<String,Object> respuesta = new HashMap<>();
        List<String> mensaje = new ArrayList<>();

         try{

             if(usu.getNombre() != null || usu.getNombre().matches(regexNombre)){
                 mensaje.add("Nombre no puede estar vacio y solo puede tener letras");
             }

             if(usu.getApellido() != null || usu.getApellido().matches(regexNombre)){
                 mensaje.add("Nombre no puede estar vacio y solo puede tener letras");
             }

             if(usu.getPassword() != null || usu.getConfirm_password() != null
                     || usu.getConfirm_password().length() > 15 || usu.getPassword().length() > 15){
                 mensaje.add("Contraseñas no puede ser vacio y mayor a 15 caracteres");

                 if(!usu.getPassword().equals(usu.getConfirm_password())){
                     mensaje.add("Contraseñas deben ser iguales");
                 }
             }

             if(usu.getEmail() != null || usu.getEmail().matches(regexEmail)){
                 mensaje.add("Email no puede ser vacio y debe cumplir con el formato");
             }

             if(usu.getNacimiento() != null){
                 mensaje.add("Fecha Nacimiento no puede ser vacio y debe ser dd-MM-yyyy");
             }


            if(mensaje.isEmpty()){
                mensaje.add("Validacion correcta");
            }

            respuesta.put("mensaje", mensaje);

         }catch (Exception e){
                String message =e.getMessage();
                mensaje.add(message);
         }

         return respuesta;
    }


    public String AutogenerarId(){

        StringBuilder inicio = new StringBuilder("USR-");
        String valores = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String[] arrayCaracteres = valores.split("");

        int min = 0;
        int max = 40;
        int numeroAleatorio;

        for(int i = 0 ; i<3 ; i++){
            numeroAleatorio = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
            inicio.append(arrayCaracteres[numeroAleatorio]);
        }
        return inicio.toString();
    }







}
