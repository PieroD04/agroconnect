package com.acme.web.services.management.domain.model.commands;

import java.time.LocalDate;
import java.util.Date;

public record UpdateResourceCommand(Long resourceId, String name, String type, Integer quantity, LocalDate date, String observations, Long breederId) {

}

