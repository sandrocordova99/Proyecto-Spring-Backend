package com.examen.integrador.Entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno_curso_categoria")
@Data
@NoArgsConstructor // Necesario para JPA
public class AlumnoCursoCategoria {

    @EmbeddedId
    private AlumnoCursoCategoriaId id;

    @ManyToOne
    @MapsId("alumnoId")
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumnos alumno;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id", nullable = false)
    private Cursos curso;
    @ManyToOne
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categoria;

    @Column(name = "fecha_inicio_aprendizaje")
    private LocalDate fechaInicioAprendizaje;

    @Column(name = "nota1")
    private Double nota1;

    @Column(name = "nota2")
    private Double nota2;

    @Column(name = "nota3")
    private Double nota3;

    public AlumnoCursoCategoria(Alumnos alumno, Cursos curso, Categorias categoria) {
        this.alumno = alumno;
        this.curso = curso;
        this.categoria = categoria;
        this.id = new AlumnoCursoCategoriaId(alumno.getId(), curso.getId(), categoria.getId());
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
        if (this.id != null)
            this.id.setAlumnoId(alumno.getId());
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
        if (this.id != null)
            this.id.setCursoId(curso.getId());
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
        if (this.id != null)
            this.id.setCategoriaId(categoria.getId());
    }
}