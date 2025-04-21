package com.examen.integrador.Servicios.Alumno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Mapper.AlumnoMapper;
import com.examen.integrador.Repositorio.AlumnosRepositorio;
import com.examen.integrador.Repositorio.UserRepositorio;

@Service
public class AlumnoServicioImp implements AlumnoServicio {

    private final PasswordEncoder passwordEncoder;
    private final AlumnosRepositorio alumnosRepositorio;
    private final UserRepositorio userRepositorio;

    @Autowired
    AlumnoServicioImp(AlumnosRepositorio alumnosRepositorio, UserRepositorio userRepositorio,
            PasswordEncoder passwordEncoder) {
        this.alumnosRepositorio = alumnosRepositorio;
        this.userRepositorio = userRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ResponseAlumnoDTO> ListAlumnos() {
        try {

            List<Alumnos> listaAlumnos = alumnosRepositorio.findAll();

            List<ResponseAlumnoDTO> ListAlumnosDTO = AlumnoMapper.instancia.ListAlumnos(listaAlumnos);
            
            return ListAlumnosDTO;
        } catch (Exception e) {

            throw new RuntimeException("Error al listar alumnos", e);
        }
    }

    @Transactional
    @Override
    public Alumnos crearAlumno(RequestAlumnoDTO dto) {

        try {

            Usuarios usu = AlumnoMapper.instancia.toUsuarioRequest(dto);

            usu.setId(AutogenerarId());
            usu.setPassword(passwordEncoder.encode(usu.getPassword()));
            usu.setConfirm_password(passwordEncoder.encode(usu.getPassword()));

            Alumnos alu = AlumnoMapper.instancia.toAlumnoRequest(dto);

            // bidireccional
            alu.setUsuarios(usu);
            usu.setAlumnos(alu);

            // Se guarda una sola vez
            userRepositorio.save(usu);

            return alu;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario y alumno", e);
        }

    }

    public String AutogenerarId() {

        StringBuilder respuesta = new StringBuilder("ALM-");
        String valores = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String[] arrayCaracteres = valores.split("");

        int min = 0;
        int max = 35;
        int numeroAleatorio;

        for (int i = 0; i < 3; i++) {
            numeroAleatorio = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
            respuesta.append(arrayCaracteres[numeroAleatorio]);
        }
        return respuesta.toString();
    }

}
