package com.examen.integrador.Controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.DTO.ProfesorDTO.AsignarGradoProfesorDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorEditDTO;
import com.examen.integrador.Servicios.Profesores.ProfesorServicioImp;
import com.examen.integrador.Validacion.UserValidacion;

@RestController
@RequestMapping("/profesor")
public class ProfesorControlador {

    private final ProfesorServicioImp profesorServicioImp;
    private final UserValidacion userValidacion;

    public ProfesorControlador(ProfesorServicioImp profesorServicioImp, UserValidacion userValidacion) {
        this.profesorServicioImp = profesorServicioImp;
        this.userValidacion = userValidacion;
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearProfesor(@RequestBody ProfesorRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Profesor", profesorServicioImp.crearProfesor(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PostMapping("/asignarGrados")
    public ResponseEntity<Map<String, Object>> asignarGrados(@RequestBody AsignarGradoProfesorDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        // Map<String, Object> respuestaValidacion =
        // userValidacion.validarUsuarios(dto);

        respuesta.put("Grados: ", profesorServicioImp.asignarGrados(dto));

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarProfesor() {

        Map<String, Object> respuesta = new HashMap();

        respuesta.put("Lista", profesorServicioImp.listarProfesores());

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Map<String, Object>> actualizarProfesor(@RequestBody ProfesorEditDTO dto) {

        Map<String, Object> respuesta = new HashMap();

        dto.setEdicion(true);

        Map<String, Object> respuestaValidacion = userValidacion.validarUsuarios(dto);

        if (respuestaValidacion.containsKey("Confirmación")) {

            ProfesorResponseDTO alu = profesorServicioImp.actualizarProfesores(dto);

            respuesta.put("validacion", respuestaValidacion.get("Confirmación"));

        } else {
            respuesta.put("Error", respuestaValidacion.get("Errores"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}