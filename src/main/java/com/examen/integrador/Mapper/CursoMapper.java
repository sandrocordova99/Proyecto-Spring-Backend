package com.examen.integrador.Mapper;

import org.mapstruct.Mapper;
 
import org.mapstruct.factory.Mappers;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
 
import com.examen.integrador.Entidades.Cursos;
 

@Mapper
public interface CursoMapper {

    CursoMapper instancia = Mappers.getMapper(CursoMapper.class);


    Cursos toCursoRequest(CursoRequestDTO dto);



    /*
      
       @Mapping(target = "usuarios", expression = "java(toUsuarioResponse(dto))")
    Alumnos toAlumnoResponse(ResponseAlumnoDTO dto);

    
    //@Mapping(target = "usuarios", expression = "java(toUsuarioRequest(dto))")
    Alumnos toAlumnoRequest(RequestAlumnoDTO dto);

    Usuarios toUsuarioRequest(RequestAlumnoDTO dto);

    // Métodos que convierten un DTO a un Usuario
    Usuarios toUsuarioResponse(ResponseAlumnoDTO dto);

    

    List<ResponseAlumnoDTO> ListAlumnos(List<Alumnos> alumnos);

    @Mapping(source = "usuarios.nombre", target = "nombre")
    @Mapping(source = "usuarios.apellido", target = "apellido")
    @Mapping(source = "usuarios.username", target = "username")
    @Mapping(source = "usuarios.email", target = "email")
    @Mapping(source = "usuarios.nacimiento", target = "nacimiento")
    @Mapping(source = "usuarios.roles", target = "roles")
    ResponseAlumnoDTO toResponseAlumnoDTO(Alumnos alumno);
     
     */
   
    
}
