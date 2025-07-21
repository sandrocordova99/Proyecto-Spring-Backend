package com.examen.integrador.Controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.integrador.DTO.CategoriaDTO.CategoriaRequestDTO;
import com.examen.integrador.Servicios.Categorias.CategoriasServicioImp;

@RestController
@RequestMapping("/cat")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriasControlador {

    private final CategoriasServicioImp categoriasServicioImp;

    @Autowired
    public CategoriasControlador(CategoriasServicioImp categoriasServicioImp) {
        this.categoriasServicioImp = categoriasServicioImp;

    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearCategorias(@RequestBody CategoriaRequestDTO dto) {

        Map<String, Object> respuesta = new HashMap();
        List<String> mensaje = new ArrayList();

        if (dto.getDescripcion().isEmpty() || dto.getDescripcion().length() < 100
                || dto.getDescripcion().length() > 200) {
            mensaje.add("Descripcion incorrecta");
        }

        if (dto.getNombre().isEmpty() || dto.getNombre().length() < 10 || dto.getNombre().length() > 50) {
            mensaje.add("Nombre incorrecta");
        }

        if (dto.getIdCursos().isEmpty()) {
            mensaje.add("Curso no puede ser vacio ");
        }

        if (dto.getIdGrados().isEmpty()) {
            mensaje.add("Grado no puede ser vacio ");
        }

        if (mensaje.isEmpty()) {
            respuesta.put("respuesta", "categoria craeada exitosamente");
            respuesta.put("Categoria ID", categoriasServicioImp.crearCategorias(dto));
        } else {
            respuesta.put("error", mensaje);
        }

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

}
