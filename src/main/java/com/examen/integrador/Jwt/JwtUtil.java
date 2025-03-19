package com.examen.integrador.Jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtUtil {

    private static final String SECRET_KEY = "EsteEsUnSecretoMuySeguroParaJWT1234"; // Debe tener al menos 32 caracteres
    private static final Key key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET_KEY.getBytes()));

    public String generarToken(String username,GrantedAuthority authority){

        String roles = authority.getAuthority();

        //los token esperan una lista de Roles en Stirng
        return Jwts.builder()
                .setSubject(username)
                .claim("roles" , roles)
                .setExpiration(new Date((System.currentTimeMillis() +  1000 * 60 * 20)))
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256) // 🔹 Usa la clave personalizada
                .compact();

    }


    public String recuperarUsuario(String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String recuperarRoles(String token){

        Object roles = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");

        //los roles se guardan como string o lista de string
        if(roles instanceof String){
            return (String) roles;
        }

        return "Usuario sin roll";
    }


    public boolean validarToken(String token){
        try{
            if(token.isEmpty()){
                return false;
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }







}
