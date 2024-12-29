package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for Expense entity.
 * It extends JpaRepository to get access to CRUD operations.
 * it contains a method to find all expenses by breeder id.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByBreederId(Long breederId);
}