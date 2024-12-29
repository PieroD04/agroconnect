package com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for the Appointment entity.
 * It provides methods to access the Appointment entity by different properties.
 * It extends the JpaRepository interface from Spring Data JPA.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByBreederId(Long breederId);
    List<Appointment> findAllByAdvisorId(Long advisorId);
}
