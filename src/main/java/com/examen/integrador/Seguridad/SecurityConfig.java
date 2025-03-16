package com.examen.integrador.Seguridad;

import com.examen.integrador.Jwt.JwtFilterChain;
import com.examen.integrador.Jwt.JwtUtil;
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

    @Autowired
    public SecurityConfig(CustomDetailsService customDetailsService, JwtFilterChain filterChain, JwtUtil jwtUtil) {
        this.customDetailsService = customDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/register").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ) .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/auth/register","/auth/login");
                })
                .formLogin().disable()
                .addFilterBefore(filterChain() ,UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

      @Bean
      public PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();
      }

      @Bean
      public JwtFilterChain filterChain(){
        return new JwtFilterChain(jwtUtil,customDetailsService);
      }

      @Bean
      public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(customDetailsService)
                    .passwordEncoder(passwordEncoder)
                    .and()
                    .build();
      }


}
