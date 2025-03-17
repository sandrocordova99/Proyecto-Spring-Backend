package com.examen.integrador.Jwt;

import com.examen.integrador.Seguridad.CustomDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilterChain extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final CustomDetailsService customDetailsService;

    @Autowired
    public JwtFilterChain(JwtUtil jwtUtil, CustomDetailsService customDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customDetailsService = customDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1.extraer
        String encabezado = request.getHeader("Authorization");

        //2.verificar
        if(encabezado != null && encabezado.startsWith("Bearer ")){

            //3.extraer el token y el username
            String token = encabezado.substring(7);
            String username = jwtUtil.recuperarUsuario(token);

            //4.validar si el usuario existe
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                //5.conseguir el usuario de la bd
                UserDetails detalleUsuario = customDetailsService.loadUserByUsername(username);

                //6.validar que el token no haya sido alterado (+importante)
                if(jwtUtil.validarToken(token)){

                    //7.crear una instancia de un usuario autenticado

                    try{
                        //7.1 Recuperar Roles
                        String roles = jwtUtil.recuperarRoles(token);
                        List<SimpleGrantedAuthority> rolesAuth = new ArrayList<>();

                       rolesAuth.add(new SimpleGrantedAuthority(roles));

                        //8.crear una instancia de un usuario autenticado
                        UsernamePasswordAuthenticationToken usuarioAutenticado = new UsernamePasswordAuthenticationToken(
                                detalleUsuario, null, rolesAuth);

                        System.out.println("Usuario autenticado: "  + usuarioAutenticado.getName());
                        System.out.println("Rol autenticado: "  + usuarioAutenticado.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }


        }

        //9.termina el filtro y pasa a la siguiente filtro
        filterChain.doFilter(request, response);

    }


}
