package com.acme.web.services.management.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * Birthdate value object represents the birthdate of an animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public record Birthdate(LocalDate birthdate) {
    public Birthdate {
        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
    }
}
