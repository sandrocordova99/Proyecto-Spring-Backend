package com.examen.integrador.Servicios.Grado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.GradoDTO.GradoRequestDTO;
import com.examen.integrador.DTO.GradoDTO.GradoResponseDTO;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Mapper.GradoMapper;
import com.examen.integrador.Repositorio.GradoRepositorio;
import com.examen.integrador.Validacion.AutogenerarID;

@Service
public class GradoServicioImp implements GradoServicio {

    private final GradoRepositorio gradoRepositorio;
    private final AutogenerarID autogenerarID;

    @Autowired
    public GradoServicioImp(GradoRepositorio gradoRepositorio, AutogenerarID autogenerarID) {
        this.gradoRepositorio = gradoRepositorio;
        this.autogenerarID = autogenerarID;
    }

    // para este punto ya deberia esta validado los campos
    @Override
    public GradoResponseDTO crearGrado(GradoRequestDTO dto) {

        try {
            Grados grados = GradoMapper.instancia.toCursoRequest(dto);

            grados.setId(autogenerarID.generarId("GRADOS"));

            return GradoMapper.instancia.toCursoReponse(gradoRepositorio.save(grados));
            
        } catch (Exception e) {

            throw new RuntimeException("Error al guardar el grado", e);

        }

    }


    @Override
    public GradoResponseDTO asignarCursos(String CursoId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'asignarCursos'");
    }

}
