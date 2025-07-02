package com.examen.integrador.DTO.ProfesorDTO;

import java.sql.Date;

import com.examen.integrador.DTO.UsuarioBaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorRequestDTO extends UsuarioBaseDTO {

    // Atributos en profesor

    private Date contratacion;

    private Double sueldo;

    private String cursoId;

}
