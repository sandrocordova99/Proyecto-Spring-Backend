package com.examen.integrador.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.Servicios.AlumnoServicioImp;
import com.examen.integrador.Servicios.UserSerivicio;

@RestController
@RequestMapping("/alu")
public class AlumnosControlador {
    
    private final AlumnoServicioImp alumnoServicioImp;

    @Autowired
    public AlumnosControlador(AlumnoServicioImp alumnoServicioImp) {
        this.alumnoServicioImp = alumnoServicioImp;
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String,Object>> listarAlumnos() {

        Map<String,Object> respuesta = new HashMap();

        List<ResponseAlumnoDTO> ListAlumnosDTO = alumnoServicioImp.ListAlumnos();

        respuesta.put("Alumnos", ListAlumnosDTO);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }


}
