package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the Breeder entity.
 * It extends the JpaRepository interface from Spring Data JPA.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
@Repository
public interface BreederRepository extends JpaRepository<Breeder, Long>{

}