package com.acme.web.services.appointment.interfaces.rest.transform;

import com.acme.web.services.appointment.domain.model.entities.Review;
import com.acme.web.services.appointment.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity){
        return new ReviewResource(
                entity.getId(),
                entity.getAppointment().getId(),
                entity.getComment(),
                entity.getRating()
        );
    }
}
