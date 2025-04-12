package com.examen.integrador.Servicios.Curso;

import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Mapper.CursoMapper;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

@Service
public class CursoServicioImp implements CursoServicio{

    private final CursosRepositorio cursosRepositorio;
    private final AutogenerarID autogenerarID;

    public CursoServicioImp(CursosRepositorio cursosRepositorio , AutogenerarID autogenerarID){
        this.cursosRepositorio = cursosRepositorio;
        this.autogenerarID = autogenerarID;
    }


    @Override
    public Cursos crearCurso(CursoRequestDTO dto) {
        
        Cursos cursos = CursoMapper.instancia.toCursoRequest(dto);

        cursos.setId(autogenerarID.generarId("CURSOS"));

        //cantidad

        cursosRepositorio.save(cursos);

        return cursos;
    }
    
}
