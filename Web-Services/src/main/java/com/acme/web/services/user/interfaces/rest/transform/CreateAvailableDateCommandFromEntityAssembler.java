package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.commands.CreateAvailableDateCommand;
import com.acme.web.services.user.interfaces.rest.resources.CreateAvailableDateResource;

public class CreateAvailableDateCommandFromEntityAssembler {
    public static CreateAvailableDateCommand toCommandFromResource(CreateAvailableDateResource resource){
        return new CreateAvailableDateCommand(
                resource.advisorId(),
                resource.date(),
                resource.startTime(),
                resource.endTime(),
                true
        );
    }
}
