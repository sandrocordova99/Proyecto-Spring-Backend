
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

    // patron singleton
    AlumnoMapper instancia = Mappers.getMapper(AlumnoMapper.class);

    Usuarios toUsuario(ResponseAlumnoDTO dto);

    // mostrar
    @Mapping(target = "usuarios", expression = "java(toUsuario(dto))")
    Alumnos toAlumnoResponse(ResponseAlumnoDTO dto);

    // guardar
    @Mapping(target = "usuarios", expression = "java(toUsuario(dto))")
    Alumnos toAlumnoRequest(RequestAlumnoDTO dto);

    Usuarios toUsuario(RequestAlumnoDTO dto);

    /* Listar alumnos */
    List<ResponseAlumnoDTO> ListAlumnos(List<Alumnos> alumnos);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")
    ResponseAlumnoDTO toResponseAlumnoDTO(Alumnos alumno);

}