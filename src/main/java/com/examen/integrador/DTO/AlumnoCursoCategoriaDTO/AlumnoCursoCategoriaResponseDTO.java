package com.examen.integrador.DTO.AlumnoCursoCategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class AlumnoCursoCategoriaResponseDTO {

    public AlumnoCursoCategoriaResponseDTO() {
        //TODO Auto-generated constructor stub
    }
    
    private String alumnoId;
    private String alumnoNombre;      // El nombre del Alumno (¡más legible que solo el ID!)
    private String cursoId;
    private String cursoNombre;       // El nombre del Curso
    private String categoriaId;
    private String categoriaNombre;   // El nombre de la Categoría
    private String fechaInicioAprendizaje; // La fecha formateada como texto
    private Double nota1;
    private Double nota2;
    private Double nota3;
}
