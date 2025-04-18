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

    private Set<String> cursos = new HashSet();
    
}
