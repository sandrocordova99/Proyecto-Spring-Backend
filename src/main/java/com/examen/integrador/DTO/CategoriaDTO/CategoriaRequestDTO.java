package com.examen.integrador.DTO.CategoriaDTO;

  

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaRequestDTO {

    private String nombre;

    private String descripcion;

    private String idCursos;

    private String idGrados;

    //Set<String> idProfesores = new HashSet();

}
