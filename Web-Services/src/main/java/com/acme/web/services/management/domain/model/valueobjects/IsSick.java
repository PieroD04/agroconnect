package com.acme.web.services.management.domain.model.valueobjects;

/**
 * This record represents the sickness of the animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record IsSick(Boolean isSick) {
    public IsSick {
        if (isSick == null) {
            throw new IllegalArgumentException("IsSick cannot be null");
        }
    }
}
