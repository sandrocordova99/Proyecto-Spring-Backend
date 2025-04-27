package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @Column(name = "nombre" , nullable = false , unique = true)
    private String nombre;

    @OneToMany(mappedBy = "grado")
    private List<Alumnos> alumnos = new ArrayList();

    @OneToMany(mappedBy = "grado" , fetch = FetchType.EAGER)
    private Set<Cursos> cursos = new HashSet();

    @Column(name = "cantidad_alumnos"   )
    private int cantidad_alumnos = 0;

}
