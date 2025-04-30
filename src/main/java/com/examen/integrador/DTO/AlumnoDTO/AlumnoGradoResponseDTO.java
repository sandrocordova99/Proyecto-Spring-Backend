package com.examen.integrador.DTO.AlumnoDTO;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlumnoGradoResponseDTO {

    private String nombre;
    private Set<String> cursos;

}
