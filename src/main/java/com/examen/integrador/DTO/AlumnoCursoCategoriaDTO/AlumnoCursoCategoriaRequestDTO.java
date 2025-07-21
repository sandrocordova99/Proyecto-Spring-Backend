package com.examen.integrador.DTO.AlumnoCursoCategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
 
@AllArgsConstructor
public class AlumnoCursoCategoriaRequestDTO {
    private String alumnoId;    // Solo el ID del Alumno
    private String cursoId;     // Solo el ID del Curso
    private String categoriaId; // Solo el ID de la Categoría
    private String fechaInicioAprendizaje; // La fecha como texto, para simplificar
    private Double nota1; // Las notas que quieres que ingresen
    private Double nota2;
    private Double nota3;
}
