package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * CreateExpenseResource represents the resource to create an expense.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */

public record CreateExpenseResource(@NotNull String name,
                                    @NotNull String type,
                                    @NotNull Float amount,
                                    @NotNull LocalDate date,
                                    @NotNull String observations,
                                    @NotNull Long breederId) {
}
