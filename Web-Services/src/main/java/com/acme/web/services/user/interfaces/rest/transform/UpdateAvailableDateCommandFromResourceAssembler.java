package com.acme.web.services.user.interfaces.rest.transform;
import com.acme.web.services.user.domain.model.commands.UpdateAvailableDateCommand;
import com.acme.web.services.user.interfaces.rest.resources.UpdateAvailableDateResource;

public class UpdateAvailableDateCommandFromResourceAssembler {
    public static UpdateAvailableDateCommand toCommandFromResource(UpdateAvailableDateResource resource, Long availableDateId) {
        return new UpdateAvailableDateCommand(
                availableDateId,
                resource.advisorId(),
                resource.date(),
                resource.startTime(),
                resource.endTime(),
                resource.status()
        );
    }
}