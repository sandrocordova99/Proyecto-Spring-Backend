package com.examen.integrador.DTO.CursoDTO;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AsignarGradoDTO {

    private String gradoId;

    private Set<String> cursosId;

}