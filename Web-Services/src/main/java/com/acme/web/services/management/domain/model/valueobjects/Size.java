package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Size value object represents the size of a cage.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public record Size(int size) {
    public Size {
        if (size <= 0) {
            throw new IllegalArgumentException("Resource size must be greater than zero");
        }
    }
}
