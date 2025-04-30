package com.examen.integrador.DTO.ProfesorDTO;

import java.time.LocalDate;
import java.util.Set;

import com.examen.integrador.Entidades.RolesEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorResponseDTO {

    private String nombre;

    private String apellido;

    private String username;

    private String password;

    private String confirm_password;

    private String email;

    private LocalDate nacimiento;

    private RolesEnum roles;

    //CUrsos y grados

    Set<String> nombreGrados;

    String nombreCurso;

}
