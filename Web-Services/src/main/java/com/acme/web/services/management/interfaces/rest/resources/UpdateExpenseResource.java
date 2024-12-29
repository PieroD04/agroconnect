package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * UpdateExpenseResource represents the resource to update an expense.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */

public record UpdateExpenseResource(String name,
                                    String type,
                                    Float amount,
                                    LocalDate date,
                                    String observations,
                                    Long breederId) {
}
