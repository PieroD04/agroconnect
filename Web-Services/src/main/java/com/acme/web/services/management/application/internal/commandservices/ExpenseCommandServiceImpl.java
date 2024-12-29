package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.exceptions.ExpenseNotFoundException;
import com.acme.web.services.management.domain.exceptions.ExpenseSaveException;
import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.model.commands.UpdateExpenseCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.ExpenseCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ExpenseRepository;
import com.acme.web.services.user.domain.exceptions.BreederNotFoundException;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class represents the service implementation for the Expense aggregate.
 * It implements the methods to create, update and delete an expense.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {
    private final ExpenseRepository expenseRepository;
    private final BreederRepository breederRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, BreederRepository breederRepository){
        this.expenseRepository = expenseRepository;
        this.breederRepository = breederRepository;
    }

    /**
     * Creates an expense in the database
     * @param command the command to create an expense
     * @return the created expense
     */

    @Override
    public Long handle(CreateExpenseCommand command) {
        //check if breeder exists
        Breeder breeder = breederRepository
                .findById(command.breederId()).orElseThrow(()
                        -> new BreederNotFoundException(command.breederId()));

        //check if expense type is valid
        try {
            ExpenseType.valueOf(command.type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid expense type");
        }
        //create and save the expense
        Expense expense = new Expense(command, breeder);
        try {
            expenseRepository.save(expense);
        } catch (Exception e) {
            throw new ExpenseSaveException("Error while saving expense", e);
        }
        return expense.getId();
    }

    /**
     * Updates an expense in the database
     * @param command the command to update an expense
     * @return the updated expense
     */
    @Override
    public Optional<Expense> handle(UpdateExpenseCommand command) {
        //check if expense type is valid
        try {
            ExpenseType.valueOf(command.type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid expense type");
        }
        return expenseRepository.findById(command.expenseId()).map(expense -> {
            expense.setName(new Name(command.name()));
            expense.setExpenseType(ExpenseType.valueOf(command.type().toUpperCase()));
            expense.setAmount(new Amount(command.amount()));
            expense.setDate(new DateOfCreation(command.date()));
            expense.setObservations(new Observations(command.observations()));
            return expenseRepository.save(expense);
        });
    }

    /**
     * Deletes an expense in the database
     * @param command the command to delete an expense
     * @return the deleted expense
     */
    @Override
    public Optional<Expense> handle(DeleteExpenseCommand command) {
        if (!expenseRepository.existsById(command.expenseId())) {
            throw new ExpenseNotFoundException(command.expenseId());
        }
        var expense = expenseRepository.findById(command.expenseId());
        expense.ifPresent(expenseRepository::delete);
        return expense;
    }
}