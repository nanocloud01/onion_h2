package com.example.onion.infrastructure.controller;

import com.example.onion.application.service.UserService;
import com.example.onion.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * --- Adaptador de Entrada (Controller) ---
 *
 * Este controlador REST es un "adaptador" que convierte las peticiones HTTP
 * en llamadas a los casos de uso de nuestra capa de aplicación (UserService).
 *
 * Es el punto de entrada desde el "mundo exterior" (un navegador, un cliente móvil, etc.).
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Endpoint para crear un nuevo usuario.
     * Responde a peticiones POST en /users.
     *
     * @param request El cuerpo de la petición, que contiene el nombre y el email.
     * @return Una respuesta HTTP 201 (Created) con la ubicación del nuevo recurso.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User createdUser = userService.createUser(request.name(), request.email());
        URI location = URI.create("/users/" + createdUser.getId());
        return ResponseEntity.created(location).body(createdUser);
    }

    /**
     * Endpoint para obtener un usuario por su ID.
     * Responde a peticiones GET en /users/{id}.
     *
     * @param id El ID del usuario, extraído de la URL.
     * @return Una respuesta HTTP 200 (OK) con el usuario si se encuentra,
     *         o 404 (Not Found) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DTO (Data Transfer Object) para la creación de un usuario.
     * Usamos un 'record' de Java para una definición concisa e inmutable.
     */
    public record CreateUserRequest(String name, String email) {}
}
