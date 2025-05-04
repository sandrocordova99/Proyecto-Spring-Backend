package com.examen.integrador.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Entidades.Profesor;
import com.examen.integrador.Entidades.Usuarios;

@Mapper(componentModel = "spring", uses = CursoMapperHelper.class)
public interface ProfesorMapper {

    Usuarios toUsuarioRequest(ProfesorRequestDTO dto);

    Usuarios toUsuarioResponse(ResponseAlumnoDTO dto);

    @Mapping(target = "curso", source = "cursoId", qualifiedByName = "mapCursoIdToCurso")
    Profesor tProfesorRequest(ProfesorRequestDTO dto);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")
    @Mapping(source = "grados", target = "nombreGrados", qualifiedByName = "mapGradoToGradoDTO")
    @Mapping(source = "curso", target = "nombreCurso", qualifiedByName = "mapCursoToCursoDTO")
    ProfesorResponseDTO toProfesorResponse(Profesor profesor);

    List<ProfesorResponseDTO> toProfesorResponseList(List<Profesor> Profesor);

    @Named("mapGradoToGradoDTO")
    default Set<String> mapGradoToGradoDTO(Set<Grados> grados) {

        return grados.stream().map(Grados::getNombre).collect(Collectors.toSet());

    }

    @Named("mapCursoToCursoDTO")
    default String mapCursoToCursoDTO(Cursos curso) {
        return curso.getNombre();
    }

}
