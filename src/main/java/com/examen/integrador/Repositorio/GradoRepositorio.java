package com.examen.integrador.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.integrador.Entidades.Grados;
 
@Repository
public interface GradoRepositorio extends JpaRepository<Grados, String>{

    
    
    
}
