package com.examen.integrador.Validacion;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserValidacion {



    public  Map<String,Object> validarUsuarios(Usuarios usu){

        String regexNombre = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\\\s]+$";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook)\\.com$";
        Map<String,Object> respuesta = new HashMap<>();
        List<String> mensaje = new ArrayList<>();

         try{

             if(usu == null){
                 mensaje.add("Usuario no puede ser nulo");
             }

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










}
