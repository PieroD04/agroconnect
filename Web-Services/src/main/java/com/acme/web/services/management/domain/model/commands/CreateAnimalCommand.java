package com.acme.web.services.management.domain.model.commands;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateAnimalCommand(
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
