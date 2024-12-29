package com.acme.web.services.user.domain.model.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAvailableDateCommand(Long advisorId, LocalDate date, LocalTime startTime, LocalTime endTime, boolean status) {
}
