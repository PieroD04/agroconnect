package com.acme.web.services.management.domain.exceptions;

/**
 * This exception is thrown when an error occurs while saving an expense in the database.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ExpenseSaveException extends RuntimeException {
    public ExpenseSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
