package com.acme.web.services.appointment.application.internal.commandservices;

import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.valueObjects.Status;
import com.acme.web.services.appointment.domain.services.AppointmentCommandService;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;

import org.springframework.stereotype.Service;

/**
 * This class represents the service implementation for the AppointmentCommandService.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;
    private final BreederRepository breederRepository;
    private final AdvisorRepository advisorRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, BreederRepository breederRepository, AdvisorRepository advisorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.breederRepository = breederRepository;
        this.advisorRepository = advisorRepository;
    }

    /**
     * Creates an appointment in the database
     * @param command the command to create an appointment
     * @return the created appointment
     */
    @Override
    public Long handle(CreateAppointmentCommand command) {
        var breeder = breederRepository.findById(command.breederId());
        var advisor = advisorRepository.findById(command.advisorId());

        if (breeder.isEmpty() || advisor.isEmpty()) {
            throw new IllegalArgumentException("Breeder or Advisor does not exist");
        }
        var appointment = new Appointment(breeder.get(), advisor.get(), command);
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving appointment: " + e.getMessage());
        }
        return appointment.getId();
    }

    /**
     * Updates an appointment in the database
     * @param command the command to update an appointment
     * @return the updated appointment
     */
    @Override
    public Long handle(UpdateAppointmentCommand command) {
        var appointment = appointmentRepository.findById(command.appointmentId());

        if (appointment.isEmpty()) {
            throw new IllegalArgumentException("Appointment does not exist");
        }

        // Convert the status string to a Status enum
        Status newStatus;
        try {
            newStatus = Status.valueOf(command.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + command.status());
        }

        appointment.get().setStatus(newStatus);
        try {
            appointmentRepository.save(appointment.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating appointment status: " + e.getMessage());
        }

        return appointment.get().getId();
    }
}