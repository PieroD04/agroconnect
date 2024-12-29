package com.acme.web.services.management.interfaces.rest;

import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.model.queries.GetAllExpensesQuery;
import com.acme.web.services.management.domain.model.queries.GetExpenseByIdQuery;
import com.acme.web.services.management.domain.services.ExpenseCommandService;
import com.acme.web.services.management.domain.services.ExpenseQueryService;
import com.acme.web.services.management.interfaces.rest.resources.CreateExpenseResource;
import com.acme.web.services.management.interfaces.rest.resources.ExpenseResource;
import com.acme.web.services.management.interfaces.rest.resources.UpdateExpenseResource;
import com.acme.web.services.management.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.acme.web.services.management.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.transform.UpdateExpenseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Expenses Controller
 * This class represents the REST controller for the Expenses Management Endpoints
 * It contains the methods to create, get all, get by ID, update and delete expenses
 * @author Salvador Antonio Salinas Torres
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/expenses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Expenses", description = "Expenses Management Endpoints")
public class ExpensesController {
    private final ExpenseCommandService expenseCommandService;
    private final ExpenseQueryService expenseQueryService;

    public ExpensesController(ExpenseCommandService expenseCommandService, ExpenseQueryService expenseQueryService) {
        this.expenseCommandService = expenseCommandService;
        this.expenseQueryService = expenseQueryService;
    }

    /**
     * POST method to create a new expense
     * @param resource CreateExpenseResource
     * @return ResponseEntity<ExpenseResource>
     */
    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@RequestBody CreateExpenseResource resource) {
        var createExpenseCommand = CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource);
        var expenseId = expenseCommandService.handle(createExpenseCommand);
        if (expenseId == 0L) return ResponseEntity.badRequest().build();
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) return ResponseEntity.badRequest().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return new ResponseEntity<>(expenseResource, HttpStatus.CREATED);
    }

    /**
     * GET method to get all expenses
     * @return ResponseEntity<List<ExpenseResource>>
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResource>> getAllExpenses() {
        var getAllExpensesQuery = new GetAllExpensesQuery();
        var expenses = expenseQueryService.handle(getAllExpensesQuery);
        var expenseResources = expenses.stream()
                .map(ExpenseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(expenseResources);
    }

    /**
     * GET method to get an expense by its ID
     * @param expenseId Long
     * @return ResponseEntity<ExpenseResource>
     */
    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> getExpenseById(@PathVariable Long expenseId) {
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) return ResponseEntity.notFound().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(expense.get());
        return ResponseEntity.ok(expenseResource);
    }

    /**
     * PUT method to update an expense by its ID
     * @param expenseId Long
     * @param resource UpdateExpenseResource
     * @return ResponseEntity<ExpenseResource>
     */
    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResource> updateExpense(@PathVariable Long expenseId, @RequestBody UpdateExpenseResource resource) {
        var updateExpenseCommand = UpdateExpenseCommandFromResourceAssembler.toCommandFromResource(expenseId, resource);
        var updateExpense = expenseCommandService.handle(updateExpenseCommand);
        if (updateExpense.isEmpty()) return ResponseEntity.notFound().build();
        var expenseResource = ExpenseResourceFromEntityAssembler.toResourceFromEntity(updateExpense.get());
        return ResponseEntity.ok(expenseResource);
    }

    /**
     * DELETE method to delete an expense by its ID
     * @param expenseId Long
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long expenseId) {
        var getExpenseByIdQuery = new GetExpenseByIdQuery(expenseId);
        var expense = expenseQueryService.handle(getExpenseByIdQuery);
        if (expense.isEmpty()) return ResponseEntity.notFound().build();
        var deleteExpenseCommand = new DeleteExpenseCommand(expenseId);
        expenseCommandService.handle(deleteExpenseCommand);
        return ResponseEntity.ok("Expense with given id successfully deleted");
    }

}
