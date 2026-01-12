package com.example.onion.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * --- Punto de Entrada de la Aplicación Spring Boot ---
 *
 * @SpringBootApplication es una anotación de conveniencia que agrega:
 *  - @Configuration: Etiqueta la clase como una fuente de definiciones de beans.
 *  - @EnableAutoConfiguration: Le dice a Spring Boot que comience a agregar beans basados en la configuración del classpath y varias propiedades.
 *  - @ComponentScan: Le dice a Spring que busque otros componentes, configuraciones y servicios en el paquete 'com.example.onion', permitiéndole encontrar los controladores.
 *
 * @EntityScan: Escanea los paquetes especificados en busca de clases de entidad (como User). Es crucial porque nuestras entidades están en un módulo diferente ('domain').
 * @EnableJpaRepositories: Habilita los repositorios de Spring Data JPA. También necesita saber dónde encontrar nuestras interfaces de repositorio.
 */
@SpringBootApplication(scanBasePackages = "com.example.onion")
@EntityScan(basePackages = "com.example.onion.domain.model")
@EnableJpaRepositories(basePackages = "com.example.onion.application.repository")
public class OnionApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnionApplication.class, args);
    }
}
