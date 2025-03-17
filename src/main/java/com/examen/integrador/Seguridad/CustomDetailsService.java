package com.examen.integrador.Seguridad;

import com.examen.integrador.Entidades.RolesEnum;
import com.examen.integrador.Entidades.Usuarios;
import com.examen.integrador.Repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/*
* Extends : Hereda todos los metodos y atributos de otra clase padre (override)
* Implements : implementa uno o mas interfaces
* */

@Service
public class CustomDetailsService implements UserDetailsService {


    private final UserRepositorio userRepositorio;

    @Autowired
    public CustomDetailsService(UserRepositorio userRepositorio) {
        this.userRepositorio = userRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Usuario: " + username);

        try{
            Usuarios usuario = userRepositorio.findUsuariosByUsername(username);

            if(usuario == null){
                throw new UsernameNotFoundException("Usuario no encontrado");
            } else {

                return new org.springframework.security.core.userdetails.User(
                        usuario.getUsername(),
                        usuario.getPassword(),
                        getAuthorities(usuario)

                );

            }

        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("Error al cargar usuario", e);
        }


    }

    private Collection<? extends GrantedAuthority> getAuthorities(Usuarios usuario) {

        RolesEnum roles = usuario.getRoles();
        System.out.println("rol: " + roles);
        if(roles == null){

            return List.of(new SimpleGrantedAuthority("ROLE_ALUMNO"));

        }

        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.name()));

    }
}
