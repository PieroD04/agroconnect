package com.acme.web.services.appointment.domain.services;

import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;

public interface ReviewCommandService {
    Long handle(CreateReviewCommand command);
}
