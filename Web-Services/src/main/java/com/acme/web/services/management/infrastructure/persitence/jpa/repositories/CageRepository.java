package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for Cage entity.
 * It extends JpaRepository to have access to CRUD operations.
 * It contains the method findAllByBreederId to find all cages by breeder id.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface CageRepository extends JpaRepository<Cage, Long> {
    List<Cage> findAllByBreederId(Long breederId);
}
