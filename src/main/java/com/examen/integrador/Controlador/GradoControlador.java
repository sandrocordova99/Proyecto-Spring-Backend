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

import com.examen.integrador.DTO.CursoDTO.AsignarGradoDTO;
import com.examen.integrador.DTO.GradoDTO.AsignarAlumnosDTO;
import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.Servicios.Grado.GradoServicioImp;

@RestController
@RequestMapping("/grado")
public class GradoControlador {
    
    private final GradoServicioImp gradoServicioImp;

    public GradoControlador(GradoServicioImp gradoServicioImp){
        this.gradoServicioImp = gradoServicioImp;
    }   

    
    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearGrados(@RequestBody GradoRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Grado", gradoServicioImp.crearGrado(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PostMapping("/asignar")
    public ResponseEntity<Map<String, Object>> asignarCursos(@RequestBody AsignarGradoDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Grado", gradoServicioImp.asignarCursos(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarCursos() {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Grado", gradoServicioImp.listGradoResponseDTO( ));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PostMapping("/asignarAlumnos")
    public ResponseEntity<Map<String, Object>> asignarAlumnos(@RequestBody AsignarAlumnosDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Grado", gradoServicioImp.asignarAlumnos(dto ));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}
