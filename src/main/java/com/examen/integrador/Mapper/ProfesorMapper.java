package com.examen.integrador.Mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
 import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
 import com.examen.integrador.Entidades.Profesor;
import com.examen.integrador.Repositorio.CursosRepositorio;

@Mapper(componentModel = "spring", uses = CursoMapperHelper.class)
public interface ProfesorMapper {

     public static final CursosRepositorio cursosRepositorio = null;

    ProfesorMapper instancia = Mappers.getMapper(ProfesorMapper.class);

    @Mapping(target = "usuarios.nombre", source = "nombre")
    @Mapping(target = "usuarios.apellido", source = "apellido")
    @Mapping(target = "usuarios.username", source = "username")
    @Mapping(target = "usuarios.email", source = "email")
    @Mapping(target = "usuarios.nacimiento", source = "nacimiento")
    @Mapping(target = "usuarios.roles", source = "roles")
    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "mapCursoIdToCurso")
    Profesor tProfesorRequest(ProfesorRequestDTO dto);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")

    ProfesorResponseDTO toProfesorResponse(Profesor profesor);

    List<ProfesorResponseDTO> toProfesorResponseList(List<Profesor> Profesor);

    
}
