package com.acme.web.services.appointment.interfaces.rest.transform;

import com.acme.web.services.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.acme.web.services.appointment.interfaces.rest.resources.UpdateAppointmentResource;

public class UpdateAppointmentCommandFromResourceAssembler {
    public static UpdateAppointmentCommand toCommandFromResource(UpdateAppointmentResource resource, Long appointmentId) {
        return new UpdateAppointmentCommand(
                appointmentId,
                resource.breederId(),
                resource.advisorId(),
                resource.date(),
                resource.status()
        );
    }
}