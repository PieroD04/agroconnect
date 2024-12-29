package com.acme.web.services.appointment.application.internal.queryservices;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsByBreederIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsByAdvisorIdQuery;
import com.acme.web.services.appointment.domain.services.AppointmentQueryService;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the service implementation for the AppointmentQueryService.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query){
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query){
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByBreederIdQuery query){
        return appointmentRepository.findAllByBreederId(query.breederId());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByAdvisorIdQuery query){
        return appointmentRepository.findAllByAdvisorId(query.advisorId());
    }
}