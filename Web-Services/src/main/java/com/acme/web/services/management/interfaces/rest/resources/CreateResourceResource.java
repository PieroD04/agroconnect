package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * CreateExpenseResource represents the resource to create an expense.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record CreateResourceResource(@NotNull String name,
                                     @NotNull String type,
                                     @NotNull Integer quantity,
                                     @NotNull LocalDate date,
                                     @NotNull String observations,
                                     @NotNull Long breederId) {
}
