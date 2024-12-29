package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.queries.GetAllExpensesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllExpensesQuery;
import com.acme.web.services.management.domain.model.queries.GetExpenseByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Expense query service interface.
 * It defines the methods that must be implemented by the ExpenseQueryServiceImpl class.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
    List<Expense> handle(GetAllExpensesByBreederIdQuery query);
}
