package com.examen.integrador.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.examen.integrador.Entidades.Categorias;

@Mapper(componentModel = "spring", uses = CategoriaMapperHelper.class)
public interface CategoriaMapper {

    CategoriaMapper instancia = Mappers.getMapper(CategoriaMapper.class);

    @Mapping(source = "idCursos", target = "cursos", qualifiedByName = "cursosDTOToCurso")
    @Mapping(source = "idGrados", target = "grados", qualifiedByName = "gradosDTOToGrados")
    @Mapping(source = "idProfesores", target = "profesores", qualifiedByName = "profesoresDTOToProfesores")
    Categorias toCategoriaRequest(CategoriaRequestDTO dto);






}
