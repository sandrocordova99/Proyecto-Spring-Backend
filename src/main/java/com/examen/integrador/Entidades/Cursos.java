package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cursos {

    @Id
    private String id;

    @Column(length = 20, nullable = false, name = "nombre", unique = true)
    private String nombre;

    @Column(nullable = true, name = "cantidad")
    private int cantidad;

    /*
     * @ManyToOne()
     * 
     * @JoinColumn(name = "grado")
     * private Grados grado;
     * 
     */ 

    @OneToMany(mappedBy = "curso")
    private Set<Profesor> profesores = new HashSet<>();

    @OneToMany(mappedBy = "cursos")
    private Set<Categorias> categorias;

    @ManyToMany(mappedBy = "cursos")
    private List<Alumnos> alumnos = new ArrayList();
}
