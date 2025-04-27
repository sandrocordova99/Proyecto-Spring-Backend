package com.examen.integrador.DTO.AlumnoDTO;

import java.time.LocalDate;

import com.examen.integrador.Entidades.RolesEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlumnoEditDTO {

    private String alumnoID;

    private String nombre;

    private String apellido;

    private String username;

    private String email;

    // Alumno
    private String nombreDeApoderado;

    private String gradoID;

}
