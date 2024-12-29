package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Resource class for creating an animal.
 * It contains the attributes needed to create an animal.
 * It contains the validation constraints for the attributes.
 * It is used by the CreateAnimalController.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record CreateAnimalResource (
    @NotNull String name,
    @NotNull String breed,
    @NotNull Boolean gender,
    @NotNull LocalDate birthdate,
    @NotNull Float weight,
    @NotNull Boolean isSick,
    @NotNull String observations,
    @NotNull Long cageId) {
}
