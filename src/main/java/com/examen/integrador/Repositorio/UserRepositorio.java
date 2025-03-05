package com.examen.integrador.Repositorio;

import com.examen.integrador.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorio extends JpaRepository<Usuarios, String> {

    Usuarios findUsuariosByUsername(String username);

}
