package com.acme.web.services.management.domain.model.commands;

import java.time.LocalDate;

public record CreateResourceCommand(String name, String type, Integer quantity, LocalDate date, String observations, Long breederId) {
}

