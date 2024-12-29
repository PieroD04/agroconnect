package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Weight value object represents the weight of an animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public record Weight(Float weight) {
    public Weight {
        if (weight == null || weight <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative or null");
        }
    }
}
