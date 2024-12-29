package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity){
        return new NotificationResource(
                entity.getId(),
                entity.getType(),
                entity.getText(),
                entity.getDate(),
                entity.getUser().getId(),
                entity.getMeetingUrl()
        );
    }
}
