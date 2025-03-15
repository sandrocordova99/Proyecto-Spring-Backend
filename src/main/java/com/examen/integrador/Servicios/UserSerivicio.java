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

        Map<String,Object> respuesta = new HashMap<>();

        try{
            if(usuarios == null){
                throw new Exception("Usuarios no encontrado");
            }

            if(userRepositorio.existsByUsername(usuarios.getUsername())){
                respuesta.put("error", "Username ya existe");
            } else {

                usuarios.setId(AutogenerarId());

                respuesta.put("mensaje", "Usuario registrado");
                respuesta.put("Usuario",  userRepositorio.save(usuarios));
            }

        }catch (Exception e){
            e.printStackTrace();
            respuesta.put("error", e.getMessage());
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
