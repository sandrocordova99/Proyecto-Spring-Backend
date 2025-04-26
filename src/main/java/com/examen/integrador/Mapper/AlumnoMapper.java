
package com.examen.integrador.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.DTO.GradoDTO.AlumnoSimpleDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Usuarios;

/*
 



 */
@Mapper
public interface AlumnoMapper {

    AlumnoMapper instancia = Mappers.getMapper(AlumnoMapper.class);

    Usuarios toUsuarioRequest(RequestAlumnoDTO dto);

    Usuarios toUsuarioResponse(ResponseAlumnoDTO dto);

    // @Mapping(target = "usuarios", expression = "java(toUsuarioRequest(dto))")
    Alumnos toAlumnoRequest(RequestAlumnoDTO dto);

    List<ResponseAlumnoDTO> ListAlumnos(List<Alumnos> alumnos);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")
    @Mapping(source = "cursos", target = "cursos", qualifiedByName = "mapCursosToDTO")
    @Mapping(source = "grado.nombre", target = "grado") // 👈 CORRECTO aquí
    ResponseAlumnoDTO toResponseAlumnoDTO(Alumnos alumno);

    @Named("mapCursosToDTO")
    default Set<String> mapCursosToDTO(List<Cursos> cursos) {
        if (cursos == null)
            return new HashSet<>();
        return cursos.stream()
                .map(Cursos::getNombre)
                .collect(Collectors.toSet());
    }

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    AlumnoSimpleDTO toAlumnoSimpleDTO(Alumnos alu);

}