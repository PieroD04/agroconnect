package com.acme.web.services.management.domain.model.commands;

public record UpdateCageCommand(Long cageId, String name, Integer size, String observations, Long breederId) {
}
