package com.example.onion.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * --- DDD Aggregate Root / Entity ---
 *
 * Anotada como @Entity para ser gestionada por JPA.
 * Representa la tabla 'users' en la base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Email email;

    /**
     * Constructor para crear un nuevo usuario antes de persistirlo.
     * El ID será generado por la base de datos.
     *
     * @param name El nombre del usuario.
     * @param email El objeto de valor Email del usuario.
     */
    public User(String name, Email email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.name = name;
        this.email = email;
    }

    /**
     * Cambia el nombre del usuario.
     * @param newName El nuevo nombre para el usuario.
     */
    public void changeName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
    }
}
