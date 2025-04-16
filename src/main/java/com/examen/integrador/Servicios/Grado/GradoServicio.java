package com.examen.integrador.Servicios.Grado;

import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;

public interface GradoServicio {

    GradoResponseDTO crearGrado(GradoRequestDTO dto);

}
