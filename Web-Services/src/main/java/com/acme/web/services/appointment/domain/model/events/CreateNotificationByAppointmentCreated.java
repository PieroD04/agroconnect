package com.acme.web.services.appointment.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * This class represents the event to create a notification by appointment created.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@Getter
public class CreateNotificationByAppointmentCreated extends ApplicationEvent {

    private final Long breederId;
    private final Long advisorId;

    /**
     * Constructor of the CreateNotificationByAppointmentCreated event.
     * @param source the source of the event
     * @param breederId the breeder id
     * @param advisorId the advisor id
     */
    public CreateNotificationByAppointmentCreated(Object source, Long breederId, Long advisorId) {
        super(source);
        this.breederId = breederId;
        this.advisorId = advisorId;
    }

}

