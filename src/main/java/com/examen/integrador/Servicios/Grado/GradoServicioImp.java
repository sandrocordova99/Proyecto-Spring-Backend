package com.examen.integrador.Servicios.Grado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.CursoDTO.AsignarGradoDTO;
import com.examen.integrador.DTO.GradoDTO.AsignarAlumnosDTO;
import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Mapper.GradoMapper;
import com.examen.integrador.Repositorio.AlumnosRepositorio;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Repositorio.GradoRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

@Service
public class GradoServicioImp implements GradoServicio {

    private final GradoRepositorio gradoRepositorio;
    private final AutogenerarID autogenerarID;
    private final CursosRepositorio cursosRepositorio;
    private final AlumnosRepositorio alumnosRepositorio;

    @Autowired
    public GradoServicioImp(GradoRepositorio gradoRepositorio, AutogenerarID autogenerarID,
            CursosRepositorio cursosRepositorio, AlumnosRepositorio alumnosRepositorio) {
        this.gradoRepositorio = gradoRepositorio;
        this.autogenerarID = autogenerarID;
        this.cursosRepositorio = cursosRepositorio;
        this.alumnosRepositorio = alumnosRepositorio;
    }

    // para este punto ya deberia esta validado los campos
    @Override
    public GradoResponseDTO crearGrado(GradoRequestDTO dto) {

        try {
            Grados grados = GradoMapper.instancia.toGradoRequest(dto);

            grados.setId(autogenerarID.generarId("GRADOS"));

            return GradoMapper.instancia.toGradoReponse(gradoRepositorio.save(grados));

        } catch (Exception e) {

            throw new RuntimeException("Error al guardar el grado", e);

        }

    }

    @Override
    public GradoResponseDTO asignarCursos(AsignarGradoDTO dto) {

        try {

            Optional<Grados> gradosOptional = gradoRepositorio.findById(dto.getGradoId());

            List<Cursos> cursosList = cursosRepositorio.findAllById(dto.getCursosId());

            Set<Cursos> listaCursos = new HashSet<>(cursosList);

            if (gradosOptional.isEmpty() || listaCursos.isEmpty()) {
                throw new UsernameNotFoundException("No se encontró grado o cursos con ese ID");
            }

            Grados grados = gradosOptional.get();

            for (Cursos curso : cursosList) {
                curso.setGrado(grados); // establecer la relación desde el lado del curso
            }

            grados.setCursos(listaCursos);

            gradoRepositorio.save(grados);

            cursosRepositorio.saveAll(cursosList);

            return GradoMapper.instancia.toGradoReponse(grados);

        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }

    }

    @Override
    public List<GradoResponseDTO> listGradoResponseDTO() {

        try {
            List<Grados> listaGrados = gradoRepositorio.findAll();

            return GradoMapper.instancia.listGradoResponseDTO(listaGrados);
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }

    }

    @Override
    public GradoResponseDTO asignarAlumnos(AsignarAlumnosDTO dto) {

        Optional<Grados> gradosOptional = gradoRepositorio.findById(dto.getGradoId());

        List<Alumnos> alumnosList = alumnosRepositorio.findAllById(dto.getAlumnos());

        Set<Alumnos> alumnosSet = new HashSet<>(alumnosList);

        if (gradosOptional.isEmpty() || alumnosList.isEmpty()) {
            throw new UsernameNotFoundException("No se encontró grado o cursos con ese ID");
        }

        Grados grado = gradosOptional.get();

        for (Alumnos alumno : alumnosSet) {
            alumno.setGrado(grado);
            alumno.setCursos(new ArrayList<>(grado.getCursos()));
        }

        alumnosRepositorio.saveAll(alumnosSet);

        grado.setAlumnos(new ArrayList<>(alumnosSet));
        gradoRepositorio.save(grado);

        return GradoMapper.instancia.toGradoReponse(grado);
         
    }

}
