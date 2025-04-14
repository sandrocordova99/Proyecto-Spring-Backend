package com.examen.integrador.Servicios.Curso;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Entidades.Cursos;

public interface CursoServicio {
    
    CursoResponseDTO crearCurso (CursoRequestDTO dto);

}
