package com.acme.web.services.management.domain.model.commands;

public record CreateCageCommand(String name, Integer size, String observations, Long breederId) {
}
