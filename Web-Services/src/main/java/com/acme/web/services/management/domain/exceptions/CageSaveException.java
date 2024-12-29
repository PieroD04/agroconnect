package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception that is thrown when an error occurs while saving a cage.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class CageSaveException extends RuntimeException{
    public CageSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
