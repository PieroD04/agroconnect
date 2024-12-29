package com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories;

import com.acme.web.services.appointment.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for the Review entity.
 * It contains the methods to find a review by appointment id and to verify if a review exists by appointment id.
 * @author Andre Gabriel Valverde Mozo -U202218899
 * @version 1.0
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findByAppointmentId(Long appointmentId);
    boolean existsByAppointmentId(Long appointmentId);
    List<Review> findAllByAppointmentIdIn(List<Long> appointmentIds);
}
