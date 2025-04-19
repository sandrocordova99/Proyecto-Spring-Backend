package com.examen.integrador.DTO.GradoDTO;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradoResponseDTO {

    private String nombre;

    private int cantidad;

    //list alumnos nombres
    private Set<AlumnoSimpleDTO> alumnos = new HashSet();

    private Set<String> cursos = new HashSet();
    
}
