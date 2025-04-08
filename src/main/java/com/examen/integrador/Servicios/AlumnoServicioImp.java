package com.examen.integrador.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.examen.integrador.DTO.AlumnoDTO.RequestAlumnoDTO;
import com.examen.integrador.DTO.AlumnoDTO.ResponseAlumnoDTO;
import com.examen.integrador.Entidades.Alumnos;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Mapper.AlumnoMapper;
import com.examen.integrador.Repositorio.AlumnosRepositorio;

@Service
public class AlumnoServicioImp implements AlumnoServicio {

    private final AlumnosRepositorio alumnosRepositorio;

    @Autowired
    AlumnoServicioImp(AlumnosRepositorio alumnosRepositorio) {
        this.alumnosRepositorio = alumnosRepositorio;
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

    @Override
    public Alumnos crearAlumno(RequestAlumnoDTO dto) {

        Usuarios usu = AlumnoMapper.instancia.toUsuario(dto);

            

        throw new UnsupportedOperationException("Unimplemented method 'crearAlumno'");
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
