package com.examen.integrador.Servicios.Profesores;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.integrador.DTO.ProfesorDTO.ProfesorRequestDTO;
import com.examen.integrador.DTO.ProfesorDTO.ProfesorResponseDTO;
import com.examen.integrador.Entidades.Profesor;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Mapper.ProfesorMapper;
import com.examen.integrador.Repositorio.ProfesoresRepositorio;
import com.examen.integrador.Repositorio.UserRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

@Service
public class ProfesorServicioImp implements ProfesorServicio {

    private final ProfesorMapper profesorMapper;
    private final ProfesoresRepositorio profesorRepositorio;
    private final AutogenerarID autogenerarID;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositorio userRepositorio;

    @Autowired
    public ProfesorServicioImp(ProfesorMapper profesorMapper, ProfesoresRepositorio profesorRepositorio,
            AutogenerarID autogenerarID, PasswordEncoder passwordEncoder, UserRepositorio userRepositorio) {
        this.autogenerarID = autogenerarID;
        this.profesorMapper = profesorMapper;
        this.profesorRepositorio = profesorRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.userRepositorio = userRepositorio;
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
            usu.setProfesores(profesor);

            userRepositorio.save(usu);


            return profesorMapper.toProfesorResponse(profesor);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el grado", e);
        }

    }

    @Override
    public List<ProfesorResponseDTO> listarProfesores() {

        throw new UnsupportedOperationException("Unimplemented method 'listarProfesores'");
    }

}
