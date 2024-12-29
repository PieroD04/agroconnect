package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents the repository for the AvailableDate entity.
 * It contains the methods to find the available dates by advisor id and to check if an available date exists by advisor id.
 * It extends the JpaRepository interface from Spring Data JPA.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findByAdvisorId(Long advisorId);
    boolean existsByAdvisorId(Long advisorId);
}
