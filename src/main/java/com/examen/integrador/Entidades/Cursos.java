package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @Column(length = 20, nullable = false, name = "nombre" , unique = true  )
    private String nombre;

    @Column(nullable = true, name = "cantidad")
    private int cantidad;

    @ManyToOne()
    @JoinColumn(
        name = "grado"
    )
    private Grados grado;

}
