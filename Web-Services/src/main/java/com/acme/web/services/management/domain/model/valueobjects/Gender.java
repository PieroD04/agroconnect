package com.acme.web.services.management.domain.model.valueobjects;

/**
 * This record represents the gender of the animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record Gender(Boolean gender) {
    public Gender {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
    }
}
