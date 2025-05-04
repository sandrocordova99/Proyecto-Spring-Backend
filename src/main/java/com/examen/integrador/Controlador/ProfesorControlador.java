package com.examen.integrador.Controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
 import com.examen.integrador.Servicios.Profesores.ProfesorServicioImp;

@RestController
@RequestMapping("/profesor")
public class ProfesorControlador {
    
    private final ProfesorServicioImp profesorServicioImp;

    public ProfesorControlador(ProfesorServicioImp profesorServicioImp){
        this.profesorServicioImp = profesorServicioImp;
    }   

    
    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearProfesor(@RequestBody ProfesorRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Profesor", profesorServicioImp.crearProfesor(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
    
    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarProfesor() {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Lista", profesorServicioImp.listarProfesores());

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}