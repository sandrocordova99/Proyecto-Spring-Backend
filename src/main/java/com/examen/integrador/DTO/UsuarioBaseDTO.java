package com.examen.integrador.DTO;

import java.time.LocalDate;
import com.examen.integrador.Entidades.RolesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioBaseDTO {

    // Usuarios

    private String nombre;

    private String apellido;

    private String username;

    private String password;

    private String confirm_password;

    private String email;

    private LocalDate nacimiento;

    private RolesEnum roles;

    private boolean edicion = false;
}