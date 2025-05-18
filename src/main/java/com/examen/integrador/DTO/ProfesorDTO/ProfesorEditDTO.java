package com.examen.integrador.DTO.ProfesorDTO;

import java.util.Set;

import com.examen.integrador.DTO.UsuarioBaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProfesorEditDTO extends UsuarioBaseDTO{

    private String id;

    // Atributos de profesores ->
    private Double sueldo;
    
    private String cursoId; 

    private Set<String> listaGrados;

}
