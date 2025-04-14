package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.List;

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

    /*
     * Muchos alumnos pueden estar en un solo grado
     * Muchos cursos se pueden dictar en un solo grado
     * 
     * No hay una relacion directa entre alumnos - cursos , en todo caso dependeria
     * del grado el curso que pueden llevar.
     */

    @Column(name = "nombre" , nullable = false , unique = true)
    private String nombre;

    @OneToMany(mappedBy = "grado")
    private List<Alumnos> alumnos = new ArrayList();

    @OneToMany(mappedBy = "cursos")
    private List<Cursos> cursos = new ArrayList();

}
