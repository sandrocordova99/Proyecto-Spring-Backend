package com.examen.integrador.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Cursos;

@Mapper
public interface CursoMapper {

   // AlumnoMapper instancia = Mappers.getMapper(AlumnoMapper.class);
   CursoMapper instancia = Mappers.getMapper(CursoMapper.class);

   Cursos toCursoRequest(CursoRequestDTO dto);

   // CursoResponseDTO toCursoReponse(Cursos cursos);

   //@Mapping(source = "grado.nombre", target = "grado")
   @Mapping(source = "alumnos", target = "cantidad", qualifiedByName = "alumnosToCantidad")
   CursoResponseDTO toCursoReponse(Cursos cursos);

   @Named("alumnosToCantidad")
   default int alumnosToCantidad(List<Alumnos> alumnos) {
      return alumnos != null ? alumnos.size() : 0;
   }

   List<CursoResponseDTO> listarCursosDTO(List<Cursos> cursos);

}
