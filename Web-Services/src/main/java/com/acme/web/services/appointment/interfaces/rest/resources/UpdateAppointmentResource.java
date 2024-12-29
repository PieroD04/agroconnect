package com.acme.web.services.appointment.interfaces.rest.resources;


import java.util.Date;

public record UpdateAppointmentResource(Long breederId, Long advisorId, Date date, String status) {
}
