package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.user.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource){
        return new CreateNotificationCommand(
                resource.type(),
                resource.text(),
                resource.date(),
                resource.userId(),
                resource.meetingUrl()
        );
    }
}
