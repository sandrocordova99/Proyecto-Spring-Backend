package com.examen.integrador.DTO.AlumnoDTO;

import java.time.LocalDate;
import com.examen.integrador.Entidades.RolesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestAlumnoDTO {

    //Usuarios
    private String id;

    private String nombre;

    private String apellido;

    private String username;

    private String password;

    private String confirm_password;

    private String email;

    private LocalDate nacimiento;

    private RolesEnum roles;

    //Alumno
    private String nombreDeApoderado;
}