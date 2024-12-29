package com.acme.web.services.user.application.internal.eventhandlers;

import com.acme.web.services.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.user.domain.services.NotificationCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import com.acme.web.services.user.interfaces.rest.resources.CreateNotificationResource;
import com.acme.web.services.user.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This class is responsible for handling the AppointmentCreatedEvent.
 * It creates a notification for the breeder and the advisor.
 * It sends the notification to the breeder and the advisor.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
@Service
public class AppointmentCreatedEventHandler {
    private final BreederRepository breederRepository;
    private final AdvisorRepository advisorRepository;
    private final NotificationCommandService notificationCommandService;

    public AppointmentCreatedEventHandler(NotificationCommandService notificationCommandService, BreederRepository breederRepository, AdvisorRepository advisorRepository) {
        this.breederRepository = breederRepository;
        this.advisorRepository = advisorRepository;
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onAppointmentCreated(CreateNotificationByAppointmentCreated event) {
        Date date = new Date();

        var breeder = breederRepository.findById(event.getBreederId()).orElseThrow();
        var advisor = advisorRepository.findById(event.getAdvisorId()).orElseThrow();

        var meetingUrl = "https://meet.jit.si/AgroConnectMeeting" + event.getBreederId() + "-" + event.getAdvisorId();


        notificationCommandService.handle(new CreateNotificationCommand("Appointment", "Tienes un asesoramiento programado con " + advisor.getFullname(), date, breeder.getUserId(), meetingUrl));
        notificationCommandService.handle(new CreateNotificationCommand("Appointment", "Tienes una asesoria programada con " + breeder.getFullname(), date, advisor.getUserId(), meetingUrl));
    }

}