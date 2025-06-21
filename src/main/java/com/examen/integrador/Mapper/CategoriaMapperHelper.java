package com.examen.integrador.Mapper;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Entidades.Profesor;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Repositorio.GradoRepositorio;
import com.examen.integrador.Repositorio.ProfesoresRepositorio;
import com.examen.integrador.Repositorio.UserRepositorio;
        
@Component
public class CategoriaMapperHelper {
    @Autowired
    private CursosRepositorio cursosRepositorio;

    @Autowired
    private GradoRepositorio gradosRepositorio;

    @Autowired
    private ProfesoresRepositorio profesorRepositorio;

    @Autowired
    private UserRepositorio userRepositorio;

    @Named("cursosDTOToCurso")
    public Cursos mapCurso(String id) {
        return cursosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
    }

    @Named("gradosDTOToGrados")
    public Grados mapGrado(String id) {
        return gradosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Grado no encontrado con id: " + id));
    }

    /*   @Named("profesoresDTOToProfesores")
    public Set<Profesor> mapProfesores(Set<String> ids) {
        return new HashSet<>(profesorRepositorio.findAllById(ids));
    } */
 
    /*@Named("profesoresToProfesoresDTO")
    public Set<String> profesoresToProfesoresDTO(Set<Profesor> profesores) {

        Set<String> profesoresNombres = new HashSet();

        for (Profesor p : profesores) {
            Optional<Usuarios> userOptional = userRepositorio.findById(p.getId());
            Usuarios user = userOptional.get();
            profesoresNombres.add(user.getNombre());
        }

        return profesoresNombres;
    } */
    

}
