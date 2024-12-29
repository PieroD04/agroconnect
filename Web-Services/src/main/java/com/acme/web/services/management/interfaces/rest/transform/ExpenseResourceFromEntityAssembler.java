package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.interfaces.rest.resources.ExpenseResource;

/**
 * This class represents the assembler for the ExpenseResource from the Expense entity.
 * @author Salvador Antonio Salinas Torres
 * @version 1.0
 */
public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense entity) {
        return new ExpenseResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getAmount(),
                entity.getDate(),
                entity.getObservations(),
                entity.getBreeder().getId());
    }
}
