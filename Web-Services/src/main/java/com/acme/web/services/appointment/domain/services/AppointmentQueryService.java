package com.acme.web.services.appointment.domain.services;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsByAdvisorIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsByBreederIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    List<Appointment> handle(GetAllAppointmentsQuery query);
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByBreederIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByAdvisorIdQuery query);
}
