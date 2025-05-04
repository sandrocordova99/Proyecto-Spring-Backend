package com.examen.integrador.Servicios.Alumno;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.integrador.DTO.AlumnoDTO.AlumnoEditDTO;
import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Mapper.AlumnoMapper;
import com.examen.integrador.Repositorio.AlumnosRepositorio;
import com.examen.integrador.Repositorio.GradoRepositorio;
import com.examen.integrador.Repositorio.UserRepositorio;

@Service
public class AlumnoServicioImp implements AlumnoServicio {

    private final PasswordEncoder passwordEncoder;
    private final AlumnosRepositorio alumnosRepositorio;
    private final UserRepositorio userRepositorio;
    private final GradoRepositorio gradoRepositorio;

    @Autowired
    AlumnoServicioImp(AlumnosRepositorio alumnosRepositorio, UserRepositorio userRepositorio,
            PasswordEncoder passwordEncoder , GradoRepositorio gradoRepositorio) {
        this.alumnosRepositorio = alumnosRepositorio;
        this.userRepositorio = userRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.gradoRepositorio = gradoRepositorio;
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

    @Override
    public ResponseAlumnoDTO actualizarAlumno(AlumnoEditDTO dto) {

        try {
            
            Optional<Usuarios> usu = userRepositorio.findById(dto.getAlumnoID());
            Optional<Alumnos> alu = alumnosRepositorio.findById(dto.getAlumnoID());
            Optional<Grados> grd = gradoRepositorio.findById(dto.getGradoID());

            if(usu.isEmpty() && alu.isEmpty() && grd.isEmpty()){
                throw new UsernameNotFoundException("Id incorrecto");
            } else {

                Usuarios usuariosUpdate = usu.get();
                Alumnos alumnosUpdate = alu.get();   
                Grados gradosUpdate = grd.get();

                usuariosUpdate.setNombre(dto.getNombre());
                usuariosUpdate.setApellido(dto.getApellido());
                usuariosUpdate.setUsername(dto.getUsername());
                usuariosUpdate.setEmail(dto.getEmail());
                usuariosUpdate.setNombre(dto.getNombre());
                
                alumnosUpdate.setNombreDeApoderado(dto.getNombreDeApoderado());
                alumnosUpdate.setGrado(gradosUpdate);
                
                userRepositorio.save(usuariosUpdate);
                alumnosRepositorio.save(alumnosUpdate);

                ResponseAlumnoDTO alumnoDTO = AlumnoMapper.instancia.toResponseAlumnoDTO(alumnosUpdate);

                return alumnoDTO;
            }
            
        } catch (Exception e) {

             throw new RuntimeException("Error al actualizar" + e);
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
