package com.examen.integrador.Validacion;

import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserValidacion {

    private final UserRepositorio userRepositorio;

    @Autowired
    public UserValidacion(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;
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
