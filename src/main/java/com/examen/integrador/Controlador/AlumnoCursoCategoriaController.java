package com.examen.integrador.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaRequestDTO;
import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaResponseDTO;
import com.examen.integrador.Servicios.Incripciones.AlumnoCursoCategoriaService;

@RestController
@RequestMapping("/inscripciones/alumnos-cursos-categorias")
public class AlumnoCursoCategoriaController {

    @Autowired
    private AlumnoCursoCategoriaService alumnoCursoCategoriaService;

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> registrarAlumnoCursoCategoria(
            @RequestBody AlumnoCursoCategoriaRequestDTO dto) {

        HashMap<String  , Object> respuesta = new HashMap<>();
        try {
             
            AlumnoCursoCategoriaResponseDTO responseDto = alumnoCursoCategoriaService.registrar(dto);
            respuesta.put("respuesta:", responseDto);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (RuntimeException e) {
            respuesta.put("ERROR:", e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
                                                                        
        }
    }

     
    @GetMapping("/listarTodos")
    public ResponseEntity<List<AlumnoCursoCategoriaResponseDTO>> obtenerTodos() {
        // El Mesero le pide la lista al Gerente
        List<AlumnoCursoCategoriaResponseDTO> dtos = alumnoCursoCategoriaService.obtenerTodos();
        // El Mesero devuelve la lista con un código 200 (OK)
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // --- Endpoint 3: OBTENER registros por ID de Alumno y ID de Curso ---
    // URL: GET
    // http://localhost:8080/api/alumnos-cursos-categorias/alumnos/{alumnoId}/cursos/{cursoId}
    // Ejemplo: GET
    // http://localhost:8080/api/alumnos-cursos-categorias/alumnos/1/cursos/101
    @GetMapping("/alumnos/{alumnoId}/cursos/{cursoId}")
    public ResponseEntity<List<AlumnoCursoCategoriaResponseDTO>> obtenerPorAlumnoYCurso(
            @PathVariable String alumnoId, // Captura el ID del alumno de la URL
            @PathVariable String cursoId) { // Captura el ID del curso de la URL
        // El Mesero le pasa los IDs al Gerente
        List<AlumnoCursoCategoriaResponseDTO> dtos = alumnoCursoCategoriaService.obtenerPorAlumnoYCurso(alumnoId,
                cursoId);
        // Si no encuentra nada, devuelve una lista vacía y 200 OK, o podrías devolver
        // 404 Not Found si lo prefieres
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}