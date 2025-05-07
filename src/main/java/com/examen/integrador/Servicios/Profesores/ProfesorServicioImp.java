package com.examen.integrador.Servicios.Profesores;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.integrador.DTO.CursoDTO.AsignarGradoDTO;
import com.examen.integrador.DTO.ProfesorDTO.AsignarGradoProfesorDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorUpdateDTO;
import com.examen.integrador.Entidades.Profesor;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Mapper.ProfesorMapper;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Repositorio.GradoRepositorio;
import com.examen.integrador.Repositorio.ProfesoresRepositorio;
import com.examen.integrador.Repositorio.UserRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesorServicioImp implements ProfesorServicio {

    private final ProfesorMapper profesorMapper;
    private final ProfesoresRepositorio profesorRepositorio;
    private final AutogenerarID autogenerarID;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositorio userRepositorio;
    private final CursosRepositorio cursosRepositorio;
    private final GradoRepositorio gradoRepositorio;

    @Autowired
    public ProfesorServicioImp(ProfesorMapper profesorMapper, ProfesoresRepositorio profesorRepositorio,
            AutogenerarID autogenerarID, PasswordEncoder passwordEncoder, UserRepositorio userRepositorio,
            CursosRepositorio cursosRepositorio, GradoRepositorio gradoRepositorio) {
        this.autogenerarID = autogenerarID;
        this.profesorMapper = profesorMapper;
        this.profesorRepositorio = profesorRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.userRepositorio = userRepositorio;
        this.cursosRepositorio = cursosRepositorio;
        this.gradoRepositorio = gradoRepositorio;
    }

    @Transactional
    @Override
    public ProfesorResponseDTO crearProfesor(ProfesorRequestDTO dto) {

        try {

            Usuarios usu = profesorMapper.toUsuarioRequest(dto);

            usu.setId(autogenerarID.generarId("PROFESOR"));

            usu.setPassword(passwordEncoder.encode(usu.getPassword()));

            usu.setConfirm_password(passwordEncoder.encode(usu.getPassword()));

            Profesor profesor = profesorMapper.tProfesorRequest(dto);

            profesor.setUsuarios(usu);

            Cursos curso = cursosRepositorio.findById(dto.getCursoId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el curso con ese ID"));

            profesor.setCurso(curso);

            usu.setProfesores(profesor);

            userRepositorio.save(usu);

            return profesorMapper.toProfesorResponse(profesor);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el grado", e);
        }

    }

    @Override
    public List<ProfesorResponseDTO> listarProfesores() {

        try {
            List<Profesor> listaProfesor = profesorRepositorio.findAll();

            return profesorMapper.toProfesorResponseList(listaProfesor);

        } catch (Exception e) {

            throw new RuntimeException("Error", e);

        }

    }

    @Override
    public ProfesorResponseDTO asignarGrados(AsignarGradoProfesorDTO asignarGradoProfesorDTO) {

        try {

            Profesor profesor = profesorRepositorio.findById(asignarGradoProfesorDTO.getProfesorId())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el profesor con ese ID"));

            Set<Grados> listaGrados = new HashSet<>();

            for (String id : asignarGradoProfesorDTO.getGradosId()) {

                Optional<Grados> gradoOptional = gradoRepositorio.findById(id);

                if (gradoOptional.isPresent()) {

                    Grados grados = gradoOptional.get();

                    listaGrados.add(grados);
                }

                profesor.setGrados(listaGrados);

                profesorRepositorio.save(profesor);
            }

            return profesorMapper.toProfesorResponse(profesor);

        } catch (Exception e) {
            throw new RuntimeException("Error al implementar grados", e);
        }

    }

    @Override
    @Transactional
    public ProfesorResponseDTO actualizarProfesores(ProfesorUpdateDTO dto) {

        Profesor profeOptional = profesorRepositorio.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el profesor con ese ID"));

        Usuarios aluOptional =  userRepositorio.findById(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException("No se encontró el profesor con ese ID"));

        
        aluOptional.setNombre(dto.getNombre());
        aluOptional.setApellido(dto.getApellido());
        aluOptional.setPassword(passwordEncoder.encode(dto.getPassword()));
        aluOptional.setConfirm_password(passwordEncoder.encode(dto.getConfirm_password()));
        aluOptional.setUsername(dto.getUsername());
        aluOptional.setEmail(dto.getEmail());


        profeOptional.setSueldo(dto.getSueldo());

        //cursos
        Cursos curso = cursosRepositorio.findById(dto.getCursoId()).orElseThrow(()-> new EntityNotFoundException("grado no encontrado"));

        profeOptional.setCurso(curso); //la actualizacion es bidireccional , osea que tambien debo actualizar cursos o bueno no che la verdad

        Set<Grados> listaGrados = new HashSet();

        //grados
        for(String id : dto.getListaGrados()){

            Grados grados = gradoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró el grado con ese ID"));

            listaGrados.add(grados);
        }

        profeOptional.setGrados(listaGrados);

        profesorRepositorio.save(profeOptional);

        return profesorMapper.toProfesorResponse(profeOptional);
    }

}
