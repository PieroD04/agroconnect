package com.acme.web.services.user.domain.exceptions;

/**
 * BreederNotFoundException is a runtime exception that is thrown when a breeder is not found in the database.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
public class BreederNotFoundException extends RuntimeException {
    public BreederNotFoundException(Long aLong) {
        super("Breeder with id " + aLong + " not found");
    }
}
