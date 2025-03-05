package com.examen.integrador.Controlador;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Seguridad.CustomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final AuthenticationManager authenticationManager;
    private final CustomDetailsService customDetailsService;

    @Autowired
    public AuthControlador(AuthenticationManager authenticationManager, CustomDetailsService customDetailsService) {
        this.authenticationManager = authenticationManager;
        this.customDetailsService = customDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Usuarios user){

        Map<String,Object> respuesta = new HashMap<>();

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            respuesta.put("mensaje", "Logeo exitoso");
            respuesta.put("Rol", auth.getAuthorities());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        }catch (BadCredentialsException e){
            respuesta.put("error de credenciales", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }
        catch (Exception e){
            respuesta.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(respuesta);
        }

    }


}
