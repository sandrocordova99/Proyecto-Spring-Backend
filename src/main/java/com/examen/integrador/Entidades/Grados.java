package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Grados {

    @Id
    private String id;

    @Column(name = "nombre" , nullable = false , unique = false)
    private String nombre;

    @Column(name = "cantidadAlumnos" , nullable = false , unique = false)
    private int cantidad;

    @OneToMany(mappedBy = "grado")
    private List<Alumnos> alumnos = new ArrayList();

    @OneToMany(mappedBy = "grado")
    private Set<Cursos> cursos = new HashSet();

}
