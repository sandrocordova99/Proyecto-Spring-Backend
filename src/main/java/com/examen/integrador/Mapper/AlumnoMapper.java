
package com.examen.integrador.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.AlumnoDTO.AlumnoGradoResponseDTO;
import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.DTO.GradoDTO.AlumnoSimpleDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Categorias;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Entidades.Usuarios;

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
    @Mapping(source = "grado", target = "grado", qualifiedByName = "mapGradoToGradoDTO")
    ResponseAlumnoDTO toResponseAlumnoDTO(Alumnos alumno);

    @Named("mapGradoToGradoDTO")
    default AlumnoGradoResponseDTO mapGradoToGradoDTO(Grados grado) {

        if (grado == null) {
            return null;
        }

        AlumnoGradoResponseDTO alumnoDTO = new AlumnoGradoResponseDTO();
        alumnoDTO.setNombre(grado.getNombre());

        Set<Categorias> cursos = grado.getCategorias();
        Set<String> cursosNombre = new HashSet<>();

        if (cursos != null) {
            for (Categorias c : cursos) {
                cursosNombre.add(c.getNombre());
            }
        }

        alumnoDTO.setCursos(cursosNombre);

        return alumnoDTO;

    }

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    AlumnoSimpleDTO toAlumnoSimpleDTO(Alumnos alu);

}