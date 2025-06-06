package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Categorias;

@Repository
public interface CategoriasRepositorio extends JpaRepository<Categorias , String>{
    
}
