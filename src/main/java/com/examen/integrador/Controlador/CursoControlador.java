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
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Servicios.Curso.CursoServicioImp;
import com.examen.integrador.Validacion.UserValidacion;

@RestController
@RequestMapping("/curso")
public class CursoControlador {

    private final CursoServicioImp cursoServicioImp;
    private final UserValidacion userValidacion;
    private final CursosRepositorio cursosRepositorio;

    @Autowired
    public CursoControlador(CursoServicioImp cursoServicioImp , UserValidacion userValidacion , CursosRepositorio cursosRepositorio) {
        this.cursoServicioImp = cursoServicioImp;
        this.userValidacion = userValidacion;
        this.cursosRepositorio = cursosRepositorio;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearCursos(@RequestBody CursoRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        if(cursosRepositorio.existsByNombre(dto.getNombre())){

            respuesta.put("error" , "Nombre ya existe");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        } 

        respuesta.put("Cursos", cursoServicioImp.crearCurso(dto));

        //CursoResponseDTO curso = cursoServicioImp.crearCurso(dto);

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
