package com.examen.integrador.Servicios.Categorias;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.integrador.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.examen.integrador.DTO.CategoriaDTO.CaterogiaResponseDTO;
import com.examen.integrador.DTO.CursoDTO.CursoResponseDTO;
import com.examen.integrador.Entidades.Categorias;
import com.examen.integrador.Entidades.Cursos;
import com.examen.integrador.Entidades.Grados;
import com.examen.integrador.Mapper.CategoriaMapper;
import com.examen.integrador.Repositorio.CategoriasRepositorio;
import com.examen.integrador.Repositorio.CursosRepositorio;
import com.examen.integrador.Repositorio.GradoRepositorio;

@Service
public class CategoriasServicioImp implements CategoriasServicio {

    private final CategoriasRepositorio categoriasRepositorio;
    private final CursosRepositorio cursosRepositorio;
    private final GradoRepositorio gradoRepositorio;

    public CategoriasServicioImp(CategoriasRepositorio categoriasRepositorio, CursosRepositorio cursosRepositorio,
            GradoRepositorio gradoRepositorio) {

        this.categoriasRepositorio = categoriasRepositorio;
        this.cursosRepositorio = cursosRepositorio;
        this.gradoRepositorio = gradoRepositorio;
    }

    @Override
    public CaterogiaResponseDTO crearCategorias(CategoriaRequestDTO dto) {

        try {

            Grados grado = gradoRepositorio.findById(dto.getIdGrados())
                    .orElseThrow(() -> new RuntimeException("No se encontro grado con ese ID : "));

            Cursos cursos = cursosRepositorio.findById(dto.getIdCursos())
                    .orElseThrow(() -> new RuntimeException("No se encontro ursos con ese ID : "));

            Categorias categoria = CategoriaMapper.instancia.toCategoriaRequest(dto);

            categoria.setCursos(cursos);

            categoria.setGrados(grado);

            return CategoriaMapper.instancia.toCategoriaResponseDTO(categoriasRepositorio.save(categoria));

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar   Categorias", e);
        }

    }

    @Override
    public List<CaterogiaResponseDTO> listarCategorias() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCategorias'");
    }

    @Override
    public CaterogiaResponseDTO actualizarCategorias() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCategorias'");
    }

    @Override
    public String eliminarCategorias(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCategorias'");
    }

}
