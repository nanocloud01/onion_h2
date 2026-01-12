package com.example.onion.application.repository;

import com.example.onion.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * --- Puerto (DDD) / Interfaz de Repositorio (Arquitectura Limpia/Cebolla) ---
 *
 * Esta interfaz ahora extiende JpaRepository<User, Long>, heredando una gran cantidad de
 * métodos de persistencia estándar (save, findById, findAll, delete, etc.) sin
 * necesidad de una implementación explícita. Spring Data JPA creará una implementación
 * en tiempo de ejecución.
 *
 * El <User, Long> especifica que este repositorio es para la entidad 'User' y que
 * su clave primaria es de tipo 'Long'.
 *
 * @Repository es una anotación de Spring que la marca como un bean de repositorio,
 * haciéndola elegible para la detección de componentes y la traducción de excepciones.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA proveerá la implementación para los métodos CRUD básicos.
    // Podemos añadir aquí métodos de consulta personalizados si los necesitamos, por ejemplo:
    // Optional<User> findByEmail(Email email);
}
