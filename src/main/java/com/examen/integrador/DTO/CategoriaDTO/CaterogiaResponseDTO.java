package com.examen.integrador.DTO.CategoriaDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CaterogiaResponseDTO {

    private String nombre;

    private String descripcion;

    private String nombreCursos;

    private String nombreGrados;

    // Set<String> profesores = new HashSet();

}
