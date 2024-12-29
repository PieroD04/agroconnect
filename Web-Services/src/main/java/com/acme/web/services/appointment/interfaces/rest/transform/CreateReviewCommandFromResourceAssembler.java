package com.acme.web.services.appointment.interfaces.rest.transform;

import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;
import com.acme.web.services.appointment.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource){
        return new CreateReviewCommand(
                resource.appointmentId(),
                resource.comment(),
                resource.rating()
        );
    }
}
