package com.acme.web.services.management.application.internal.queryservices;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.queries.GetAllExpensesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllExpensesQuery;
import com.acme.web.services.management.domain.model.queries.GetExpenseByIdQuery;
import com.acme.web.services.management.domain.services.ExpenseQueryService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {
    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> handle(GetAllExpensesQuery query){ return expenseRepository.findAll(); }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query){
        return expenseRepository.findById(query.expenseId());
    }

    @Override
    public List<Expense> handle(GetAllExpensesByBreederIdQuery query) {
        return expenseRepository.findAllByBreederId(query.breederId());
    }

}
