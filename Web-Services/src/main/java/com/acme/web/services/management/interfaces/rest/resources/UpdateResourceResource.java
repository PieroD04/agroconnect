package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * Resource class that represents the resource to be updated.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public record UpdateResourceResource(String name,
                                     String type,
                                     Integer quantity,
                                     LocalDate date,
                                     String observations,
                                     Long breederId) {
}
