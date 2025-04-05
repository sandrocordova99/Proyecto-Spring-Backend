package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Administradores;

@Repository
public interface AdminRepositorio extends JpaRepository<Administradores, String>{
        
}
