package com.examen.integrador.Servicios.Curso;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.CursoDTO.CursoRequestDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Mapper.CursoMapper;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

@Service
public class CursoServicioImp implements CursoServicio {

    private final CursosRepositorio cursosRepositorio;
    private final AutogenerarID autogenerarID;

    public CursoServicioImp(CursosRepositorio cursosRepositorio, AutogenerarID autogenerarID) {
        this.cursosRepositorio = cursosRepositorio;
        this.autogenerarID = autogenerarID;
    }

    @Override
    public CursoResponseDTO crearCurso(CursoRequestDTO dto) {

        try {

            Cursos cursos = CursoMapper.instancia.toCursoRequest(dto);

            cursos.setId(autogenerarID.generarId("CURSOS"));

            // cantidad

            cursosRepositorio.save(cursos);

            return CursoMapper.instancia.toCursoReponse(cursos);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el curso", e);
        }

    }

    @Override
    public List<CursoResponseDTO> listarCursosDTO() {

        try {
            List<Cursos> cursosList = cursosRepositorio.findAll();

            List<CursoResponseDTO> cursosListDTO = CursoMapper.instancia.listarCursosDTO(cursosList);

            return cursosListDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al listar cursos", e);
        }

    }

    @Override
    public String eliminarCurso(String id) {

        try {
            Cursos cursoDelete = cursosRepositorio.findById(id)
                    .orElseThrow(() -> new UsernameNotFoundException("No se encontro el curso con ese ID"));

            for (Alumnos alumno : cursoDelete.getAlumnos()) {
                alumno.getCursos().remove(cursoDelete); // Importante: remover el curso del alumno
            }
            cursoDelete.getAlumnos().clear();
            cursosRepositorio.save(cursoDelete);
            cursosRepositorio.delete(cursoDelete);

            return cursoDelete.getNombre();

        } catch (Exception e) {
            throw new RuntimeException("Error al borrar curso: ", e);
        }

    }

}
