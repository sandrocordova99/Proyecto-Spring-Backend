package com.examen.integrador.DTO.AlumnoDTO;

 
import com.examen.integrador.DTO.UsuarioBaseDTO;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestAlumnoDTO extends UsuarioBaseDTO {

   
    // Alumno
    private String nombreDeApoderado;
}