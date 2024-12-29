package com.acme.web.services.management.domain.model.commands;

import java.time.LocalDate;

public record CreateExpenseCommand(String name, String type, Float amount, LocalDate date, String observations, Long breederId) {
}