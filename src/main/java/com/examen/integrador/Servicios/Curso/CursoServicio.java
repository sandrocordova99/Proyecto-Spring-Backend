package com.examen.integrador.Servicios.Curso;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
 
public interface CursoServicio {
    
    CursoResponseDTO crearCurso (CursoRequestDTO dto);

}
