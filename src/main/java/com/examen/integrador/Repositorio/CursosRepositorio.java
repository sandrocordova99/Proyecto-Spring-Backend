package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Cursos;
 
@Repository
public interface CursosRepositorio extends JpaRepository<Cursos, String>{
    
}
