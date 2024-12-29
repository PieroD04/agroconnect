package com.acme.web.services.appointment.domain.model.commands;

import java.util.Date;

public record UpdateAppointmentCommand(
        Long appointmentId,
        Long breederId,
        Long advisorId,
        Date date,
        String status
) {}