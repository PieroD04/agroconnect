package com.acme.web.services.management.domain.model.valueobjects;

/**
 * Quantity value object represents the quantity of a resource.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
public record Quantity(Integer quantity) {
    public Quantity {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Resource quantity must be a positive integer");
        }
    }

}

