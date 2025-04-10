
package com.examen.integrador.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Usuarios;


@Mapper
public interface AlumnoMapper {

    AlumnoMapper instancia = Mappers.getMapper(AlumnoMapper.class);

    // ✅ Cambiar expresión con el método correcto para el tipo de DTO
    @Mapping(target = "usuarios", expression = "java(toUsuarioResponse(dto))")
    Alumnos toAlumnoResponse(ResponseAlumnoDTO dto);

    // ✅ Cambiar expresión con el método correcto para el tipo de DTO
    @Mapping(target = "usuarios", expression = "java(toUsuarioRequest(dto))")
    Alumnos toAlumnoRequest(RequestAlumnoDTO dto);

    // Métodos que convierten un DTO a un Usuario
    Usuarios toUsuarioResponse(ResponseAlumnoDTO dto);

    Usuarios toUsuarioRequest(RequestAlumnoDTO dto);

    List<ResponseAlumnoDTO> ListAlumnos(List<Alumnos> alumnos);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")
    ResponseAlumnoDTO toResponseAlumnoDTO(Alumnos alumno);
}