package com.examen.integrador.Repositorio;

import com.examen.integrador.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorio extends JpaRepository<Usuarios, String> {

    Usuarios findUsuariosByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM Usuarios u WHERE u.username = :username")
    boolean existsByUsername(@Param("username")String username);



}
