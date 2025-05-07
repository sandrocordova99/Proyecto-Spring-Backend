package com.examen.integrador.DTO.ProfesorDTO;

 
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProfesorUpdateDTO {

    private String id;

    private String nombre;  

    private String apellido;

    private String username;

    private String password;

    private String confirm_password;

    private String email;

    private Double sueldo;

 
    //Atributos de profesores -> 

    private String cursoId;    

    private Set<String> listaGrados;
    
}
