package com.acme.web.services.appointment.domain.model.commands;

import java.util.Date;

public record CreateAppointmentCommand(Long breederId, Long advisorId, Date date, String status) {
}
