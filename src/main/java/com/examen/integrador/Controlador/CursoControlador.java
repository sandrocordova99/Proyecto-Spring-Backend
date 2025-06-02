package com.examen.integrador.Controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.integrador.DTO.CursoDTO.CursoEditDTO;
import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Servicios.Curso.CursoServicioImp;

@RestController
@RequestMapping("/curso")
public class CursoControlador {

    private final CursoServicioImp cursoServicioImp;

    @Autowired
    public CursoControlador(CursoServicioImp cursoServicioImp) {
        this.cursoServicioImp = cursoServicioImp;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearCursos(@RequestBody CursoRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Cursos", cursoServicioImp.crearCurso(dto));

        CursoResponseDTO curso = cursoServicioImp.crearCurso(dto);

        System.out.println("Curso nombre " + curso.getNombre());

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarCursos() {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Cursos", cursoServicioImp.listarCursosDTO());

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Map<String, Object>> eliminarCursos(@PathVariable("id") String id) {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Cursos", cursoServicioImp.eliminarCurso(id));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarCursos(@RequestBody CursoEditDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Cursos", cursoServicioImp.editarCurso(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        
    }

}
