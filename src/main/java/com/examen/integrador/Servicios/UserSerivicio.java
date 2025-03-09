package com.examen.integrador.Servicios;

import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerivicio {

    private final UserRepositorio userRepositorio;

    @Autowired
    public UserSerivicio(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;
    }

    public String registrarUsuario(){

    }


}
