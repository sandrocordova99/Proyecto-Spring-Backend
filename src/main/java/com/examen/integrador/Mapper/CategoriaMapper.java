package com.examen.integrador.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
 
import com.examen.integrador.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.examen.integrador.DTO.CategoriaDTO.CaterogiaResponseDTO;
import com.examen.integrador.Entidades.Categorias;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;

@Mapper(componentModel = "spring", uses = CategoriaMapperHelper.class)
public interface CategoriaMapper {

    //CategoriaMapper instancia = Mappers.getMapper(CategoriaMapper.class);

    // usar streams
    @Mapping(source = "idCursos", target = "cursos", qualifiedByName = "cursosDTOToCurso")
    @Mapping(source = "idGrados", target = "grados", qualifiedByName = "gradosDTOToGrados")
    //@Mapping(source = "idProfesores", target = "profesores", qualifiedByName = "profesoresDTOToProfesores")
    Categorias toCategoriaRequest(CategoriaRequestDTO dto);

    @Mapping(source = "cursos", target = "nombreCursos", qualifiedByName = "cursoToCursosDTO")
    @Mapping(source = " grados", target = "nombreGrados", qualifiedByName = "gradosToGradosDTO")
    //@Mapping(source = "profesores", target = "profesores", qualifiedByName = "profesoresToProfesoresDTO")
    CaterogiaResponseDTO toCategoriaResponseDTO(Categorias dto);

    List<CaterogiaResponseDTO> listarCategoriasDTO(List<Categorias> Categorias);

    @Named("cursoToCursosDTO")
    default String cursoToCursosDTO(Cursos cursos) {

        return cursos.getNombre();
    }

    @Named("gradosToGradosDTO")
    default String gradosToGradosDTO(Grados grados) {
        return grados.getNombre();
    }

}
