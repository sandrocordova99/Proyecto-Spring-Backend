package com.examen.integrador.DTO.GradoDTO;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AsignarAlumnosDTO {

    private String gradoId; //grado para asignar.

    private Set<String> alumnos;// alumnos para asignar al grado.

}
