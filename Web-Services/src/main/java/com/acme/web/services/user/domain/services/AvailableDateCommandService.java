package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateAvailableDateCommand;
import com.acme.web.services.user.domain.model.commands.DeleteAvailableDateCommand;
import com.acme.web.services.user.domain.model.commands.UpdateAvailableDateCommand;
import com.acme.web.services.user.domain.model.entities.AvailableDate;

import java.util.Optional;

public interface AvailableDateCommandService {
    Long handle(CreateAvailableDateCommand command);
    Optional<AvailableDate> handle(UpdateAvailableDateCommand command);
    void handle(DeleteAvailableDateCommand command);
}
