package com.examen.integrador.Servicios.Incripciones;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaRequestDTO;
import com.examen.integrador.DTO.AlumnoCursoCategoriaDTO.AlumnoCursoCategoriaResponseDTO;
import com.examen.integrador.Entidades.AlumnoCursoCategoria;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Categorias;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Mapper.AlumnoCursoCategoriaMapper;
import com.examen.integrador.Repositorio.AlumnoCursoCategoriaRepository;
import com.examen.integrador.Repositorio.AlumnosRepositorio;
import com.examen.integrador.Repositorio.CategoriasRepositorio;
import com.examen.integrador.Repositorio.CursosRepositorio;

import jakarta.transaction.Transactional;

@Service // Le dice a Spring que esta es la clase "Gerente"
public class AlumnoCursoCategoriaService {

    // Aquí "inyectamos" las herramientas que el Gerente necesita
    @Autowired private AlumnoCursoCategoriaRepository alumnoCursoCategoriaRepository;
    @Autowired private AlumnosRepositorio alumnosRepository;       // <--- ¡REVISA: ESTE ES EL NOMBRE DE TU REPOSITORIO DE ALUMNOS!
    @Autowired private CursosRepositorio cursosRepository;         // <--- ¡REVISA: ESTE ES EL NOMBRE DE TU REPOSITORIO DE CURSOS!
    @Autowired private CategoriasRepositorio categoriasRepository; // <--- ¡REVISA: ESTE ES EL NOMBRE DE TU REPOSITORIO DE CATEGORIAS!
    @Autowired private AlumnoCursoCategoriaMapper mapper;

    // --- Trabajo 1: Registrar un alumno en una categoría de un curso ---
    @Transactional // Le dice a Spring que esta operación debe ser "atómica" (todo o nada)
    public AlumnoCursoCategoriaResponseDTO registrar(AlumnoCursoCategoriaRequestDTO dto) {
        // 1. El Gerente busca los ingredientes principales (Alumno, Curso, Categoria)
        // .orElseThrow() significa: si no lo encuentro, lanzo un error
        Alumnos alumno = alumnosRepository.findById(dto.getAlumnoId())
                                        .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + dto.getAlumnoId()));
        Cursos curso = cursosRepository.findById(dto.getCursoId())
                                     .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + dto.getCursoId()));
        Categorias categoria = categoriasRepository.findById(dto.getCategoriaId())
                                                 .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        // 2. El Gerente le da los ingredientes al Cocinero Traductor para que prepare la entidad
        AlumnoCursoCategoria alumnoCursoCategoria = mapper.toEntity(dto, alumno, curso, categoria);

        // 3. El Gerente le dice a la Despensa que guarde la entidad
        AlumnoCursoCategoria savedEntity = alumnoCursoCategoriaRepository.save(alumnoCursoCategoria);

        // 4. El Gerente le dice al Cocinero Traductor que prepare el "Plato Listo" para el mesero
        return mapper.toDto(savedEntity);
    }

    // --- Trabajo 2: Obtener todos los registros de Alumno-Curso-Categoría ---
    public List<AlumnoCursoCategoriaResponseDTO> obtenerTodos() {
        // El Gerente le pide todo a la Despensa, luego el Cocinero Traductor convierte cada uno a "Plato Listo"
        return alumnoCursoCategoriaRepository.findAll().stream()
                .map(mapper::toDto) // Convierte cada entidad a su DTO de respuesta
                .collect(Collectors.toList()); // Recolecta todo en una lista
    }

    // --- Trabajo 3: Obtener las categorías que un alumno aprende en un curso específico ---
    public List<AlumnoCursoCategoriaResponseDTO> obtenerPorAlumnoYCurso(String alumnoId, String cursoId) {
        // El Gerente le pide a la Despensa los registros específicos, luego los traduce
        return alumnoCursoCategoriaRepository.findById_AlumnoIdAndId_CursoId(alumnoId, cursoId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}