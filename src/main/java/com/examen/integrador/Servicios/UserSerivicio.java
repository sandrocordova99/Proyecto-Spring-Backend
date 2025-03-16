package com.examen.integrador.Servicios;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserSerivicio {

    private final UserRepositorio userRepositorio;

    @Autowired
    public UserSerivicio(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;
    }

    public Map<String,Object> registrarUsuario(Usuarios usuarios){

        //generar id y rol

        Map<String,Object> respuesta = new HashMap<>();

        try{
            if(usuarios == null){
                throw new Exception("Usuarios no encontrado");
            }

            if(userRepositorio.existsByUsername(usuarios.getUsername())){
                respuesta.put("error", "Username ya existe");
            } else {



                respuesta.put("mensaje", "Usuario registrado");
                respuesta.put("Usuario",  userRepositorio.save(usuarios));
            }

        }catch (Exception e){
            e.printStackTrace();
            respuesta.put("error", e.getMessage());
        }

        return respuesta;

    }






}
