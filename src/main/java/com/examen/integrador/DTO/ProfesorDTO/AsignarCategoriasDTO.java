package com.examen.integrador.DTO.ProfesorDTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AsignarCategoriasDTO {

    List<String> categoriasId;

    String profesorId;
}
