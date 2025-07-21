package com.examen.integrador.Entidades;

import java.util.HashSet;
import java.util.Set;

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
public class Categorias {

    @Id
    private String id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "cursos")
    private Cursos cursos;

    @ManyToOne()
    @JoinColumn(name = "grados")
    private Grados grados;

    @ManyToMany(mappedBy = "categorias")
    Set<Profesor> profesores = new HashSet();

}
