package com.examen.integrador.Servicios.Curso;

import java.util.List;

import com.examen.integrador.DTO.CursoDTO.CursoEditDTO;
import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
 
public interface CursoServicio {
    
    CursoResponseDTO crearCurso (CursoRequestDTO dto);

    List<CursoResponseDTO> listarCursosDTO ( );

    String eliminarCurso(String id);

    CursoResponseDTO editarCurso (CursoEditDTO dto);

}
