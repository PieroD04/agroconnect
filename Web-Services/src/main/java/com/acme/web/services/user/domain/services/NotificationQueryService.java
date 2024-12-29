package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.domain.model.queries.GetAllNotificationsQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsQuery query);
    Optional<Notification> handle(GetNotificationByIdQuery query);
    List<Notification> handle(GetNotificationsByUserIdQuery query);
}
