package com.acme.web.services.user.interfaces.rest.resources;
import com.acme.web.services.user.domain.model.valueobjects.LocalTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateAvailableDateResource(Long advisorId, LocalDate date,
                                          @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime startTime,
                                          @JsonDeserialize(using = LocalTimeDeserializer.class) LocalTime endTime, boolean status) {
}