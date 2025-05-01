package com.examen.integrador.Servicios.Profesores;

import java.util.List;

import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.Entidades.Profesor;

public interface ProfesorServicio {

    ProfesorResponseDTO crearProfesor(ProfesorRequestDTO dto);

    List<ProfesorResponseDTO> listarProfesores();

}
