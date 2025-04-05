package com.examen.integrador.Entidades;

 
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Alumnos {

    @Id
    private String id;

    @Column(length = 100)
    private String grado;

    @MapsId   
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarios;

}
