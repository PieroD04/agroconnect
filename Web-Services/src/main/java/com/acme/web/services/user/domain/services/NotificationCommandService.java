package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.user.domain.model.commands.DeleteNotificationCommand;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
