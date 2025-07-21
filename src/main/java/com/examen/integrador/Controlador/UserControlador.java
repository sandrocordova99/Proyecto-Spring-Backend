package com.examen.integrador.Controlador;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Servicios.UserSerivicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserControlador {

    private final UserSerivicio userSerivicio;

    @Autowired
    public UserControlador(UserSerivicio userSerivicio) {
        this.userSerivicio = userSerivicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar() {

        Map<String, Object> respuesta = userSerivicio.listarTodosUsuarios();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listarAdmin")
    public ResponseEntity<Map<String, Object>> listarAdmin() {

        Map<String, Object> respuesta = userSerivicio.listarAdmins();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listarAlumnos")
    public ResponseEntity<Map<String, Object>> listarAlumnos() {

        Map<String, Object> respuesta = userSerivicio.listarAlumnos();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listarProfesores")
    public ResponseEntity<Map<String, Object>> listarProfesores() {

        Map<String, Object> respuesta = userSerivicio.listarProfesores();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable("id") String id) {

        Map<String, Object> respuesta = userSerivicio.eliminarUsuario(id);

        if (respuesta.containsKey("error")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }
    }

    @PutMapping("/editarUsuarios/{id}")
    public ResponseEntity<Map<String, Object>> editarAlumnos(@PathVariable("id") String id,
            @RequestBody Usuarios usuarios) {

        Map<String, Object> respuesta = userSerivicio.editarUsuario(usuarios, id);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

}
