package com.acme.web.services.management.domain.exceptions;

/**
 * This class represents the exception for when an animal is not found in the system.
 * This exception is thrown when an expense is not found in the database.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(Long expenseId) {
        super("Expense with id " + expenseId + " not found");
    }
}
