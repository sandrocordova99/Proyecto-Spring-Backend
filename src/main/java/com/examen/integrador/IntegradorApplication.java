package com.examen.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@EnableJpaRepositories(basePackages = "com.examen.integrador.Repositorio")
@EntityScan(basePackages = "com.examen.integrador.Entidades")
@SpringBootApplication
public class IntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegradorApplication.class, args);


    }

}
