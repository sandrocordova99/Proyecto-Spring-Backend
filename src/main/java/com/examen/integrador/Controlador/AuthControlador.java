package com.examen.integrador.Controlador;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Jwt.JwtUtil;
import com.examen.integrador.Seguridad.CustomDetailsService;
import com.examen.integrador.Servicios.UserSerivicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserSerivicio userSerivicio;

    @Autowired
    public AuthControlador(AuthenticationManager authenticationManager, CustomDetailsService customDetailsService, JwtUtil jwtUtil, UserSerivicio userSerivicio) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userSerivicio = userSerivicio;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Usuarios user){

        Map<String,Object> respuesta = new HashMap<>();

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            GrantedAuthority authority = auth.getAuthorities().stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontró un rol para el usuario"));

            String token = jwtUtil.generarToken(auth.getName() , authority);


            respuesta.put("mensaje", "Logeo exitoso");
            respuesta.put("token", token);
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


    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registrarUsuario(@RequestBody Usuarios user){

        Map<String,Object> respuesta = new HashMap<>();

        try{

            respuesta.put("respuesta" , userSerivicio.registrarUsuario(user));
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        }
        catch (Exception e){
            respuesta.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(respuesta);
        }

    }


}
