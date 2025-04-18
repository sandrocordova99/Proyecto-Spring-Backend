package com.examen.integrador.Seguridad;

import com.examen.integrador.Jwt.JwtFilterChain;
import com.examen.integrador.Jwt.JwtUtil;
import com.examen.integrador.Servicios.Token.TokenServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomDetailsService customDetailsService;
    private final JwtUtil jwtUtil;
    private final TokenServicio tokenServicio;

    @Autowired
    public SecurityConfig(CustomDetailsService customDetailsService, JwtFilterChain filterChain, JwtUtil jwtUtil,
            TokenServicio tokenServicio) {
        this.customDetailsService = customDetailsService;
        this.jwtUtil = jwtUtil;
        this.tokenServicio = tokenServicio;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/user/listar").hasRole("ADMIN")
                                .requestMatchers("/user/listarAdmin").hasRole("ADMIN")
                                .requestMatchers("/user/listarAlumnos").hasRole("ADMIN")
                                .requestMatchers("/user/listarProfesores").hasRole("ADMIN")
                                .requestMatchers("/user/eliminarUsuario/{id}").hasRole("ADMIN")
                                .requestMatchers("/user/editarUsuarios/{id}").hasRole("ADMIN")
                                .requestMatchers("/auth/logout").hasAnyRole("ADMIN", "PROFESOR", "ALUMNO")
                                .requestMatchers("/alu/listar").hasAnyRole("ADMIN", "PROFESOR")
                                .requestMatchers("/alu/crear").hasAnyRole("ADMIN")
                                .requestMatchers("/auth/register").hasRole("ADMIN")
                                .requestMatchers("/curso/crear").hasRole("PROFESOR")
                                .requestMatchers("/curso/listar").hasAnyRole("ADMIN", "PROFESOR")
                                .requestMatchers("/grado/crear").hasRole("ADMIN")
                                .requestMatchers("/grado/asignar").hasRole("ADMIN")
                                .requestMatchers("/grado/listar").hasAnyRole("ADMIN", "PROFESOR")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated())
                                /* .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/auth/register", "/auth/login", "/auth/logout", "/user/listar",
                            "/user/listarAdmin", "/user/listarAlumnos", "/user/listarProfesores",
                            "/user/eliminarUsuario/{id}", "/user/editarUsuarios/{id}", "/alu/listar", "/alu/crear",
                            "/curso/crear", "/grado/crear");
                }) */
               
                .formLogin().disable()
                .addFilterBefore(filterChain(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilterChain filterChain() {
        return new JwtFilterChain(jwtUtil, customDetailsService, tokenServicio);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

}
