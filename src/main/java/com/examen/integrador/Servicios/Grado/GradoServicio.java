package com.examen.integrador.Servicios.Grado;

import java.util.List;

import com.examen.integrador.DTO.GradoDTO.AsignarGradoDTO;
import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
 
public interface GradoServicio {
    
    GradoResponseDTO crearGrado(GradoRequestDTO dto);

    GradoResponseDTO asignarCursos(AsignarGradoDTO dto);

    List<GradoResponseDTO> listGradoResponseDTO();    

}
