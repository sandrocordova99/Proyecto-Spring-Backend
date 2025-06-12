package com.examen.integrador.DTO.SeccionDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeccionResponseDTO {
    
    private String nombre;

    private String descripcion;

    private String gradoNombre;

    private int cantidadAlumnos;

}
