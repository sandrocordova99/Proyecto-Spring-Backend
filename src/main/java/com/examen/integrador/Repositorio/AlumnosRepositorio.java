package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Alumnos;

@Repository
public interface AlumnosRepositorio extends JpaRepository<Alumnos, String>{
    
}
