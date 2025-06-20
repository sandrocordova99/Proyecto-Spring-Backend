package com.examen.integrador.Servicios.Categorias;

import java.util.List;

import com.examen.integrador.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.examen.integrador.DTO.CategoriaDTO.CaterogiaResponseDTO;

public interface CategoriasServicio {

    CaterogiaResponseDTO crearCategorias(CategoriaRequestDTO dto);

    // listar Categoria por ID
    List<CaterogiaResponseDTO> listarCategorias();

    // editar Categoria  
    CaterogiaResponseDTO actualizarCategorias(/*EDITAR ALGO DTO */);

    // eliminar Categoria 
    String eliminarCategorias(String id);
}
