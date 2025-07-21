package com.examen.integrador.Repositorio;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.AlumnoCursoCategoria;
import com.examen.integrador.Entidades.AlumnoCursoCategoriaId;

@Repository
public interface AlumnoCursoCategoriaRepository extends JpaRepository<AlumnoCursoCategoria, AlumnoCursoCategoriaId> {

    
    // Método para encontrar registros específicos por ID de Alumno y ID de Curso
    // Spring Boot "adivina" cómo hacer la consulta por el nombre del método.
    List<AlumnoCursoCategoria> findById_AlumnoIdAndId_CursoId(String alumnoId, String cursoId);

    // Método para encontrar registros por un conjunto de IDs de categoría (útil para el profesor)
    // Esto te permitirá buscar todos los alumnos/cursos que están aprendiendo CUALQUIERA de las categorías dadas.
    List<AlumnoCursoCategoria> findById_CategoriaIdIn(Set<String> categoriaIds);
}
 