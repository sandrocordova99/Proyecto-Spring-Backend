package com.examen.integrador.Jwt;

import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Jwts;
import java.util.Base64;

public class JwtUtil {

    private static final String key = Base64.getEncoder().encodeToString("MiClaveSandro".getBytes());

    public String generarToken(String username,GrantedAuthority authority){

        String roles = authority.getAuthority();

        return Jwts.builder()

    }


}
