package com.examen.integrador.Entidades;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  
/* Serializable sirve para crear llaves compuestas y otras cosas */
public class AlumnoCursoCategoriaId implements Serializable {

    private String alumnoId;
    private String cursoId;
    private String categoriaId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AlumnoCursoCategoriaId that = (AlumnoCursoCategoriaId) o;
        return Objects.equals(alumnoId, that.alumnoId) &&
                Objects.equals(cursoId, that.cursoId) &&
                Objects.equals(categoriaId, that.categoriaId);
    }

    @Override
    /* Une las 3 claves y crea una nueva de estas */
    public int hashCode() {
        return Objects.hash(alumnoId, cursoId, categoriaId);
    }
 
}
