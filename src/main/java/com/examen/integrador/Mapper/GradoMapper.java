package com.examen.integrador.Mapper;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;

@Mapper
public interface GradoMapper {

    GradoMapper instancia = Mappers.getMapper(GradoMapper.class);

    Grados toGradoRequest(GradoRequestDTO dto);

    @Mapping(source = "cursos", target = "cursos")
    GradoResponseDTO toGradoReponse(Grados grados);

    default Set<String> mapCursosToNombres(Set<Cursos> cursos) {
        return cursos.stream()
                .map(Cursos::getNombre)
                .collect(Collectors.toSet());
    }
    
    List<GradoResponseDTO> listGradoResponseDTO(List<Grados> grados);


}
