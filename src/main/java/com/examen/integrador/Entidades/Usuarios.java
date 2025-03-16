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

    @Column(nullable = false , length = 15 , unique = false)
    private String password;

    @Column(nullable = false , length = 15 , unique = false)
    private String confirm_password;

    @Column(nullable = false , length = 100 , unique = false)
    private String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false  , updatable = false)
    private LocalDate nacimiento;

    @Enumerated(value = EnumType.STRING)
    private RolesEnum roles;



















    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
    public RolesEnum getRoles() {
        return roles;
    }

    public void setRoles(RolesEnum roles) {
        this.roles = roles;
    }

    public Usuarios(){

    }

    public Usuarios(String apellido, String confirm_password, String email, String id, LocalDate nacimiento, String nombre, String username, RolesEnum roles, String password) {
        this.apellido = apellido;
        this.confirm_password = confirm_password;
        this.email = email;
        this.id = id;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.username = username;
        this.roles = roles;
        this.password = password;
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
