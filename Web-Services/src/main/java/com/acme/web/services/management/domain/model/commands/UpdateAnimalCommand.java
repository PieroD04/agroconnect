package com.acme.web.services.management.domain.model.commands;

import java.time.LocalDate;

public record UpdateAnimalCommand(
        Long animalId,
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
