package com.acme.web.services.management.application.internal.queryservices;

import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsByCageIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsQuery;
import com.acme.web.services.management.domain.model.queries.GetAnimalByIdQuery;
import com.acme.web.services.management.domain.services.AnimalQueryService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is used to implement the AnimalQueryService interface
 */

@Service
public class AnimalQueryServiceImpl implements AnimalQueryService {
    private final AnimalRepository animalRepository;

    public AnimalQueryServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> handle(GetAllAnimalsQuery query) {
        return this.animalRepository.findAll();
    }

    @Override
    public Optional<Animal> handle(GetAnimalByIdQuery query) {
        return  this.animalRepository.findById(query.animalId());
    }

    /**
     * This method is used to get all animals by cage id
     * @param query
     * @return
     */
    @Override
    public List<Animal> handle(GetAllAnimalsByCageIdQuery query) {
        return this.animalRepository.findAllByCageId(query.cageId());
    }
}
