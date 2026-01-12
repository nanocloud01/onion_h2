package com.example.onion.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * --- DDD Value Object ---
 *
 * Representa el correo electrónico de un usuario. Es un objeto inmutable y se compara por valor.
 * Anotado con @Embeddable, significa que sus campos se almacenarán en la misma tabla
 * que la entidad que lo contiene (en este caso, User).
 */
@Embeddable
@Getter
@NoArgsConstructor // Requerido por JPA
public class Email {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    @Column(name = "email", nullable = false, unique = true)
    private String value;

    /**
     * Constructor para crear un objeto Email validado.
     *
     * @param value La dirección de correo a validar.
     * @throws IllegalArgumentException si el formato del correo no es válido.
     */
    public Email(String value) {
        if (value == null || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Formato de email inválido: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
