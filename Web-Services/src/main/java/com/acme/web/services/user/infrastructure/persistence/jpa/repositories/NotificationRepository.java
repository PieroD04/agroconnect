package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for the Notification entity.
 * It contains the method to find the notifications by user id and to check if a notification exists by user id.
 * It extends the JpaRepository interface from Spring Data JPA.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
