package com.examen.integrador.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaRequestDTO;
import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaResponseDTO;
import com.examen.integrador.Entidades.AlumnoCursoCategoria;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Categorias;
import com.examen.integrador.Entidades.Cursos;

@Component
public class AlumnoCursoCategoriaMapper {

    // --- Trabajo 1: De PEDIDO (DTO de Creación) a INGREDIENTES CRUDOS (Entidad)
    // ---
    public AlumnoCursoCategoria toEntity(AlumnoCursoCategoriaRequestDTO dto, Alumnos alumno, Cursos curso,
            Categorias categoria) {
        // Si alguno de los ingredientes es nulo, no podemos crear la entidad.
        if (dto == null || alumno == null || curso == null || categoria == null) {
            // Puedes lanzar una excepción más específica aquí si quieres
            throw new IllegalArgumentException(
                    "Datos insuficientes para crear AlumnoCursoCategoria. Alumno, Curso o Categoría son nulos.");
        }

        // Crea la entidad usando el constructor que creamos en el Paso 2
        // Este constructor necesita los objetos Alumno, Curso y Categoria COMPLETOs
        AlumnoCursoCategoria entity = new AlumnoCursoCategoria(alumno, curso, categoria);

        // Intenta convertir la fecha de String a LocalDate
        try {
            if (dto.getFechaInicioAprendizaje() != null && !dto.getFechaInicioAprendizaje().isEmpty()) {
                entity.setFechaInicioAprendizaje(LocalDate.parse(dto.getFechaInicioAprendizaje()));
            }
        } catch (DateTimeParseException e) {
            // Si la fecha que llegó del frontend no tiene el formato correcto, lo
            // manejamos.
            System.err.println("Error al parsear fecha: " + dto.getFechaInicioAprendizaje() + " - " + e.getMessage());
            // Podrías lanzar una excepción personalizada para el usuario (ej. "Fecha
            // inválida")
            throw new IllegalArgumentException("Formato de fecha de inicio de aprendizaje inválido. Use YYYY-MM-DD.");
        }

        // Asigna las notas directamente
        entity.setNota1(dto.getNota1());
        entity.setNota2(dto.getNota2());
        entity.setNota3(dto.getNota3());

        return entity;
    }

    // --- Trabajo 2: De INGREDIENTES CRUDOS (Entidad) a PLATO LISTO (DTO de
    // Respuesta) ---
    public AlumnoCursoCategoriaResponseDTO toDto(AlumnoCursoCategoria entity) {
        // Si no hay entidad, no hay plato.
        if (entity == null) {
            return null; // O lanzar una excepción si prefieres
        }

        AlumnoCursoCategoriaResponseDTO dto = new AlumnoCursoCategoriaResponseDTO();

        // Rellena el DTO con los IDs y los NOMBRES
        dto.setAlumnoId(entity.getAlumno().getId());
        dto.setAlumnoNombre(
                entity.getAlumno().getUsuarios().getNombre() + entity.getAlumno().getUsuarios().getApellido()); // <---
                                                                                                                // ¡Asegúrate
                                                                                                                // que
                                                                                                                // tu
                                                                                                                // clase
                                                                                                                // Alumnos
                                                                                                                // tenga
                                                                                                                // un
                                                                                                                // método
        // getNombre()!

        dto.setCursoId(entity.getCurso().getId());
        dto.setCursoNombre(entity.getCurso().getNombre()); // <--- ¡Asegúrate que tu clase Cursos tenga un método
                                                           // getNombre()!

        dto.setCategoriaId(entity.getCategoria().getId());
        dto.setCategoriaNombre(entity.getCategoria().getNombre()); // <--- ¡Asegúrate que tu clase Categorias tenga un
                                                                   // método getNombre()!

        // Convierte la fecha de LocalDate a String para el DTO
        if (entity.getFechaInicioAprendizaje() != null) {
            dto.setFechaInicioAprendizaje(entity.getFechaInicioAprendizaje().toString());
        }

        dto.setNota1(entity.getNota1());
        dto.setNota2(entity.getNota2());
        dto.setNota3(entity.getNota3());

        return dto;
    }
}