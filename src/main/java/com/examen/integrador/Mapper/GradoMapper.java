package com.examen.integrador.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
import com.examen.integrador.Entidades.Grados;

@Mapper
public interface GradoMapper {

    GradoMapper instancia = Mappers.getMapper(GradoMapper.class);

    Grados toCursoRequest(GradoRequestDTO dto);

    GradoResponseDTO toCursoReponse(Grados grados);

    
}
