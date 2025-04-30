package com.examen.integrador.Mapper;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.GradoDTO.AlumnoSimpleDTO;
import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;

@Mapper
public interface GradoMapper {

    GradoMapper instancia = Mappers.getMapper(GradoMapper.class);

    Grados toGradoRequest(GradoRequestDTO dto);

    @Mapping(source = "cursos", target = "cursos", qualifiedByName = "cursosToNombres")
    @Mapping(source = "alumnos", target = "alumnos", qualifiedByName = "alumnosToSimpleDTO")
    @Mapping(source = "alumnos", target = "cantidad", qualifiedByName = "alumnosToCantidad")
    GradoResponseDTO toGradoReponse(Grados grados);

    @Named("alumnosToCantidad")
    default int alumnosToCantidad(List<Alumnos> alumnos) {
        return  alumnos != null  ? alumnos.size() : 0 ;
    }

    @Named("cursosToNombres")
    default Set<String> cursosToNombres(Set<Cursos> cursos) {
        return cursos.stream()
                .map(Cursos::getNombre)
                .collect(Collectors.toSet());
    }

    @Named("alumnosToSimpleDTO")
    default Set<AlumnoSimpleDTO> alumnosToSimpleDTO(List<Alumnos> alumnos) {
        AlumnoMapper alumnoMapper = AlumnoMapper.instancia;
        return alumnos.stream()
                .map(alumnoMapper::toAlumnoSimpleDTO)
                .collect(Collectors.toSet());
    }

    List<GradoResponseDTO> listGradoResponseDTO(List<Grados> grados);

}
