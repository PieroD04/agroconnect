package com.acme.web.services.unit.tests;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;

import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTests {
    // The following test is an example of the creation of a Expense object
    @Test
    public void testExpenseCreation() {
        Breeder breeder = new Breeder();
        Name name = new Name("Expense1");
        ExpenseType type = ExpenseType.ALIMENTO;
        Amount amount = new Amount(100.0f);
        DateOfCreation date = new DateOfCreation(LocalDate.now());
        Observations observations = new Observations("Observations");
        Expense expense = new Expense(name, type, amount, date, observations, breeder);
        assertNotNull(expense);
        assertEquals("Expense1", expense.getName());
        assertEquals(ExpenseType.ALIMENTO.toString(), expense.getType());
        assertEquals(100.0f, expense.getAmount());
        assertEquals("Observations", expense.getObservations());
    }

    // The following test is an example of the creation of a Expense object with a command
    @Test
    public void testExpenseCreationWithCommand() {
        Breeder breeder = new Breeder();
        Long breederId = breeder.getId();
        CreateExpenseCommand command = new CreateExpenseCommand("Expense1", "SALUD", 100.0f, LocalDate.now(), "Observations", breederId);
        Expense expense = new Expense(command, breeder);
        assertNotNull(expense);
        assertEquals("Expense1", expense.getName());
        assertEquals(ExpenseType.SALUD.toString(), expense.getType());
        assertEquals(100.0f, expense.getAmount());
        assertEquals("Observations", expense.getObservations());
    }

    @Test
    public void testExpenseCreationWithPositiveAmount() {
        Breeder breeder = new Breeder();
        Name name = new Name("Expense1");
        ExpenseType type = ExpenseType.ALIMENTO;
        DateOfCreation date = new DateOfCreation(LocalDate.now());
        Observations observations = new Observations("Observations");
        Amount amount = new Amount(100.0f); // Positive amount

        assertDoesNotThrow(() -> {
            Expense expense = new Expense(name, type, amount, date, observations, breeder);
        });
    }
}
