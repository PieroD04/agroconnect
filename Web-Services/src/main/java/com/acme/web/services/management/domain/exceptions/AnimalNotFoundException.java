package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception for when an animal is not found in the system.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(Long animalId) {
        super("Animal with id " + animalId + " not found");
    }
}
