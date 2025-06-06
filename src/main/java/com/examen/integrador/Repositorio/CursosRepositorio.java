package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Cursos;
 
@Repository
public interface CursosRepositorio extends JpaRepository<Cursos, String>{
    
    @Query("SELECT COUNT(u) > 0 FROM Cursos u WHERE u.nombre = :nombre")
    boolean existsByNombre(@Param("nombre")String nombre);


}
