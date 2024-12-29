package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsByCageIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsQuery;
import com.acme.web.services.management.domain.model.queries.GetAnimalByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the Animal Query Service.
 * It contains the methods that the Animal Query Service should implement.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface AnimalQueryService {
    List<Animal> handle(GetAllAnimalsQuery query);
    Optional<Animal> handle(GetAnimalByIdQuery query);
    List<Animal> handle(GetAllAnimalsByCageIdQuery query);
}
