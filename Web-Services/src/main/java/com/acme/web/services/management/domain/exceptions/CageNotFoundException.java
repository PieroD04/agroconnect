package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception for when a cage is not found in the system.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class CageNotFoundException extends RuntimeException{
    public CageNotFoundException(Long cageId) {
        super("Cage with id " + cageId + " not found");
    }
}
