package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Seccion;

@Repository
public interface SeccionesRepositorio extends JpaRepository<Seccion, String>{
    
}
