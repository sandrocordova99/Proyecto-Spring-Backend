package com.examen.integrador.DTO.GradoDTO;

import java.util.ArrayList;
import java.util.List;

 import com.examen.integrador.Entidades.Cursos;

 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradoResponseDTO {

    private String nombre;

    private int cantidad;

    private List<Cursos> cursos = new ArrayList();

}
