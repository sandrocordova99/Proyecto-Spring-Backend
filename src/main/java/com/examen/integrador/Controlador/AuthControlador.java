package com.examen.integrador.Controlador;

import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Entidades.UsuariosLogin;
import com.examen.integrador.Jwt.JwtUtil;
import com.examen.integrador.Servicios.UserSerivicio;
import com.examen.integrador.Servicios.Token.TokenServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserSerivicio userSerivicio;
    private final TokenServicio tokenServicio;

    @Autowired
    public AuthControlador(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserSerivicio userSerivicio , TokenServicio tokenServicio ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userSerivicio = userSerivicio;
        this.tokenServicio = tokenServicio;

    }


    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UsuariosLogin user){

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


    @PostMapping("/logout")
    public ResponseEntity<Map<String,Object>> logout(@RequestHeader("Authorization") String authorization){

        Map<String,Object> respuesta = new HashMap<>();

        /*pon lo que no se debe hacer*/
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

            tokenServicio.addTokenToBlackList(token);

            System.out.println("Token: " +  token);

            respuesta.put("mensaje" , "Logout  exitoso");

            return ResponseEntity.status(HttpStatus.OK).body(respuesta);

        } else {
            respuesta.put("error" , "Error al cerrar sesion");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(respuesta);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registrarUsuario(@RequestBody Usuarios user){

        Map<String,Object> respuesta = new HashMap<>();

        Map<String,Object> respuestaMetodoRegistrar = userSerivicio.registrarUsuario(user);

        try{
            if(respuestaMetodoRegistrar.containsKey("Confirmación")){

                respuesta.put("Confirmacion" , respuestaMetodoRegistrar.get("Confirmación"));

                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
            } else {
                respuesta.put("Error" , respuestaMetodoRegistrar.get("Error"));

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            }

        }
        catch (Exception e){
            respuesta.put("ErrorTry", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(respuesta);
        }

    }


}
