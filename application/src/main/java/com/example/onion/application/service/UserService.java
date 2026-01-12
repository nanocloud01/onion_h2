package com.example.onion.application.service;

import com.example.onion.application.repository.UserRepository;
import com.example.onion.domain.model.Email;
import com.example.onion.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * --- Servicio de Aplicación (DDD / Arquitectura Limpia/Cebolla) ---
 *
 * Anotado con @Service para que Spring lo gestione como un bean.
 * @RequiredArgsConstructor de Lombok crea un constructor para los campos finales,
 * facilitando la inyección de dependencias.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Caso de uso: Crear un nuevo usuario.
     *
     * @Transactional asegura que toda la operación se ejecute dentro de una transacción.
     * Si algo falla, la transacción se revierte.
     *
     * @param name El nombre del usuario.
     * @param emailValue La dirección de correo del usuario.
     * @return El usuario que ha sido creado y guardado en la base de datos (con su ID).
     * @throws IllegalArgumentException si los datos de entrada no son válidos.
     */
    @Transactional
    public User createUser(String name, String emailValue) {
        // 1. Validar y crear objetos de valor del dominio.
        Email email = new Email(emailValue);

        // 2. Crear la entidad del dominio.
        User newUser = new User(name, email);

        // 3. Usar el repositorio para persistir la nueva entidad.
        // El método save de JpaRepository devuelve la entidad guardada (con el ID ya asignado).
        return userRepository.save(newUser);
    }

    /**
     * Caso de uso: Encontrar un usuario por su ID.
     *
     * @Transactional(readOnly = true) optimiza la transacción para operaciones de solo lectura.
     *
     * @param id El ID del usuario a buscar.
     * @return Un Optional conteniendo el usuario si se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
