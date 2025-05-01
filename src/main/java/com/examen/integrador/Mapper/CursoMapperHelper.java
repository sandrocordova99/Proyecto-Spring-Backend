package com.examen.integrador.Mapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Repositorio.CursosRepositorio;

@Component
public class CursoMapperHelper {
    @Autowired
    private CursosRepositorio cursosRepositorio;

    @Named("mapCursoIdToCurso")
    public Cursos mapCursoIdToCurso(String cursoId) {
        if (cursoId == null || cursoId.isBlank())
            return null;
        return cursosRepositorio.findById(cursoId).orElse(null);
    }
}
