package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception that is thrown when a resource cannot be saved in the database.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ResourceSaveException extends RuntimeException {
    public ResourceSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}