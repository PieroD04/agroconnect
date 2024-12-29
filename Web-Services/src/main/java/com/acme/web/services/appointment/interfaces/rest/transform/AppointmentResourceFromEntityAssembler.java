package com.acme.web.services.appointment.interfaces.rest.transform;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.interfaces.rest.resources.AppointmentResource;


public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity){
        return new AppointmentResource(
                entity.getId(),
                entity.getBreeder().getId(),
                entity.getAdvisor().getId(),
                entity.getAppointmentDate(),
                entity.getStatus().name() // Convert the Status enum to a String
        );
    }
}