package com.examen.integrador.DTO.ProfesorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorAlumnoDetalleDTO {
    private String alumnoId;
    private String alumnoNombre;
    private String alumnoApellido;
    private String cursoId;
    private String cursoNombre;
    private String categoriaId;
    private String categoriaNombre;
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private String fechaInicioAprendizaje;
}
