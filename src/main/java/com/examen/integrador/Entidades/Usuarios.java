package com.examen.integrador.Entidades;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    private String id;

    @Column(nullable = false , length = 100 , unique = false)
    private String nombre;

    @Column(nullable = false , length = 100 , unique = false)
    private String apellido;

    @Column(nullable = false , length = 100 , unique = true )
    private String username;

    @Column(nullable = false , length = 100 , unique = false)
    private String password;

    @Column(nullable = false , length = 100 , unique = false)
    private String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate nacimiento;

    @Enumerated(value = EnumType.STRING)
    private RolesEnum roles;




















    public RolesEnum getRoles() {
        return roles;
    }

    public void setRoles(RolesEnum roles) {
        this.roles = roles;
    }

    public Usuarios(){

    }

    public Usuarios(String apellido, String email, String id, LocalDate nacimiento, String nombre, String password, String username) {
        this.apellido = apellido;
        this.email = email;
        this.id = id;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.password = password;
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
