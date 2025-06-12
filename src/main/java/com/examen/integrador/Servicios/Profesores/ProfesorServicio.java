package com.examen.integrador.Servicios.Profesores;

import java.util.List;

import com.examen.integrador.DTO.ProfesorDTO.AsignarGradoProfesorDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorEditDTO;

public interface ProfesorServicio {

    ProfesorResponseDTO crearProfesor(ProfesorRequestDTO dto);

    List<ProfesorResponseDTO> listarProfesores();

    ProfesorResponseDTO asignarGrados(AsignarGradoProfesorDTO grados);

    ProfesorResponseDTO actualizarProfesores(ProfesorEditDTO dto);

    ProfesorResponseDTO asignarCategorias();

    
}
