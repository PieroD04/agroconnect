package com.acme.web.services.user.application.internal.queryservices;

import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.domain.model.queries.GetAllNotificationsQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationsByUserIdQuery;
import com.acme.web.services.user.domain.services.NotificationQueryService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query){
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query){
        return notificationRepository.findById(query.notificationId());
    }

    @Override
    public List<Notification> handle(GetNotificationsByUserIdQuery query){
        return notificationRepository.findByUserId(query.userId());
    }

}
