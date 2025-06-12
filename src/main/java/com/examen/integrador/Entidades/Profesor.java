package com.examen.integrador.Entidades;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Profesor {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date contratacion;

    @Column(nullable = false, updatable = true)
    private Double sueldo;

    @ManyToMany
    @JoinTable(name = "profesor_grados", joinColumns = @JoinColumn(name = "profesor_id"), inverseJoinColumns = @JoinColumn(name = "grado_id"))
    Set<Grados> grados = new HashSet();

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos curso;

    @ManyToMany
    @JoinTable(
        name = "profesor_categorias", 
        joinColumns = @JoinColumn(name = "profesor_id"), 
        inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    Set<Categorias> categorias = new HashSet();

}
