package com.examen.integrador.Servicios.Token;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenServicio {


    //primero un contenedor de Tokens
    private final Set<String> listaTokens = new HashSet<>();

    public void addTokenToBlackList(String token){
        listaTokens.add(token);
    }

    public boolean checkToken(String token){
        return listaTokens.contains(token);
    }


}
