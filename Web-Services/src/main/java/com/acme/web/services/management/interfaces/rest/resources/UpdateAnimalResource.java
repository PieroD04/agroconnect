package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * UpdateAnimalResource represents the data to update an animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record UpdateAnimalResource(
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
