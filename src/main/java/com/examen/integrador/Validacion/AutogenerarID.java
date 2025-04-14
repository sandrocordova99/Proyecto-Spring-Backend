package com.examen.integrador.Validacion;

import org.springframework.stereotype.Service;

@Service
public class AutogenerarID {

    public String generarId(String Tipo) {

        StringBuilder respuesta = null; // = new StringBuilder("ALM-");
        String valores = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String[] arrayCaracteres = valores.split("");

        int min = 0;
        int max = 35;
        int numeroAleatorio;

        switch (Tipo.toUpperCase()) {
            case "CURSOS":
                respuesta = new StringBuilder("CRS-");
                break;

            case "ADMIN":
                respuesta = new StringBuilder("ADM-");
                break;

            case "ALUMNOS":
                respuesta = new StringBuilder("ALU-");
                break;

            case "PROFESOR":
                respuesta = new StringBuilder("PRF-");
                break;

            default:
                throw new IllegalArgumentException("Tipo no válido: " + Tipo);
        }

        for (int i = 0; i < 3; i++) {
            numeroAleatorio = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
            respuesta.append(arrayCaracteres[numeroAleatorio]);
        }
        return respuesta.toString();

    }

}
