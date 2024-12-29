package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the Advisor entity.
 * It extends from JpaRepository to have access to CRUD operations.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
}