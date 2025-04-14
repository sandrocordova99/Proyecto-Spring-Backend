package com.examen.integrador.Mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Entidades.Cursos;

@Mapper 
public interface CursoMapper {


   //  AlumnoMapper instancia = Mappers.getMapper(AlumnoMapper.class);
   CursoMapper instancia = Mappers.getMapper(CursoMapper.class);

   Cursos toCursoRequest(CursoRequestDTO dto);

   CursoResponseDTO toCursoReponse(Cursos cursos);

}
