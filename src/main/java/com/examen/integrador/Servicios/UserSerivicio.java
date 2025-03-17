package com.examen.integrador.Servicios;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import com.examen.integrador.Validacion.UserValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSerivicio {

    private final UserRepositorio userRepositorio;
    private final UserValidacion userValidacion;

    @Autowired
    public UserSerivicio(UserRepositorio userRepositorio, UserValidacion userValidacion) {
        this.userRepositorio = userRepositorio;
        this.userValidacion = userValidacion;
    }

    public Map<String,Object> registrarUsuario(Usuarios usuarios){

        Map<String,Object> respuesta = new HashMap<>();

        try{

            usuarios.setId(AutogenerarId());

            if(usuarios.getRoles() == null){
                usuarios.setRoles(RolesEnum.ALUMNO);
            }

            Map<String,Object> resultadoValidacion =userValidacion.validarUsuarios(usuarios);

            if(resultadoValidacion.containsKey("Confimacion")){
                userRepositorio.save(usuarios);
                respuesta.put("Confirmación" , resultadoValidacion.get("Confimacion"));
                respuesta.put("ID" , usuarios.getId());
            } else {
                respuesta.put("Error" ,resultadoValidacion.get("Error"));
            }

        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();

            // Agregar más información al error
            respuesta.put("error", e.getMessage());
            respuesta.put("detalle", stackTrace); //
        }

        return respuesta;

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
