package com.examen.integrador.Controlador;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Servicios.UserSerivicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserControlador {

    private final UserSerivicio userSerivicio;

    @Autowired
    public UserControlador(UserSerivicio userSerivicio) {
        this.userSerivicio = userSerivicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String,Object>> listar() {

        Map<String, Object> respuesta = userSerivicio.listarTodosUsuarios();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listarAdmin")
    public ResponseEntity<Map<String,Object>> listarAdmin() {

        Map<String, Object> respuesta = userSerivicio.listarAdmins();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listarAlumnos")
    public ResponseEntity<Map<String,Object>> listarAlumnos() {

        Map<String, Object> respuesta = userSerivicio.listarAlumnos();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }



}
