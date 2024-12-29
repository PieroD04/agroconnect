package com.acme.web.services.management.domain.exceptions;

/**
 * This exception is thrown when a resource with the same name already exists in the database.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ResourceNameAlreadyExistsException extends RuntimeException {
    public ResourceNameAlreadyExistsException(String name) {
        super("Resource with name " + name + " already exists");
    }
}
