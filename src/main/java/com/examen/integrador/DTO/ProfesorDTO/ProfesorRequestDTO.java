package com.examen.integrador.DTO.ProfesorDTO;
import java.sql.Date;
import java.time.LocalDate;
import com.examen.integrador.Entidades.RolesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorRequestDTO {

    private String nombre;

    private String apellido;

    private String username;

    private String password;

    private String confirm_password;

    private String email;

    private LocalDate nacimiento;

    private RolesEnum roles;

    //Atributos en profesor

    private Date contratacion;

    private Double sueldo;

    private String cursoId;

}
