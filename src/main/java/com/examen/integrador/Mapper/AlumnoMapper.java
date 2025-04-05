
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

    Usuarios toUsuario(RequestAlumnoDTO requestAlumnoDTO);

    Alumnos toAlumno(RequestAlumnoDTO requestAlumnoDTO);

    List<Alumnos> toListAlumnos(List<ResponseAlumnoDTO> responseAlumnoDTO);

    // setear el campo alumno en usuarios
    @Mapping(target = "usuarios", expression = "java(toUsuario(dto))")
    Alumnos toAlumnoConUsuario(RequestAlumnoDTO dto);

}