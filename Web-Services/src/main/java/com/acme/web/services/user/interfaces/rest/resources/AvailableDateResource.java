package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;


public record AvailableDateResource(Long id, Long advisorId, LocalDate date, LocalTime startTime, LocalTime endTime, boolean status) {
}
