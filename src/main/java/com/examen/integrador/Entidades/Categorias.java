package com.examen.integrador.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categorias {

    @Id
    private String id;

    @Column(name = "nombre" , nullable = false)
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "cursos")
    private Cursos cursos;

    @ManyToOne()
    @JoinColumn(name = "grados")
    private Grados grados;

}
