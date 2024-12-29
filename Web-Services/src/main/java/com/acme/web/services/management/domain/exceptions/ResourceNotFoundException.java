package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception that is thrown when a resource cannot be found in the database.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long resourceId) {
        super("Resource with id " + resourceId + " not found");
    }
}
