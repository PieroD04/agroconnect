package com.acme.web.services.appointment.domain.services;

import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.commands.UpdateAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    Long handle(UpdateAppointmentCommand command);
}
