package com.examen.integrador.Seguridad;

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

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    private final CustomDetailsService customDetailsService;

    @Autowired
    public SecurityConfig(CustomDetailsService customDetailsService) {
        this.customDetailsService = customDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

           httpSecurity
                  .csrf(csrf -> csrf.disable())
                  .authorizeHttpRequests(
                          auth -> auth
                                  .requestMatchers("/auth/login").permitAll()
                                  .anyRequest().authenticated()
                  )
                  .formLogin().disable();

           return httpSecurity.build();
      }

      @Bean
      public PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();
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
