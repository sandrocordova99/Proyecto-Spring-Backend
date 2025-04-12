package com.examen.integrador.DTO.AlumnoDTO;

import java.time.LocalDate;
import java.util.List;

import com.examen.integrador.DTO.CursoDTO.CursosSimpleDTO;
import com.examen.integrador.Entidades.RolesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAlumnoDTO {

    // Usuarios
    private String id;

    private String nombre;

    private String apellido;

    private String username;

    private String email;

    private LocalDate nacimiento;

    private RolesEnum roles;

    // Alumno
    private String nombreDeApoderado;

    // Cursos
    //private List<CursosSimpleDTO> cursos;
}