package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.List;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cursos {

    @Id
    private String id;

    @ManyToMany(mappedBy = "cursos")
    private List<Alumnos> alumnos = new ArrayList();

    @Column(length = 20, nullable = false, name = "Nombre")
    private String nombre;

    private int cantidad;

}
