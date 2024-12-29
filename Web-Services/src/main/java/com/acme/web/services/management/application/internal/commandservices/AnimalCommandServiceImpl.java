package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.exceptions.AnimalNotFoundException;
import com.acme.web.services.management.domain.exceptions.AnimalSaveException;
import com.acme.web.services.management.domain.exceptions.CageNotFoundException;
import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.domain.model.commands.DeleteAnimalCommand;
import com.acme.web.services.management.domain.model.commands.UpdateAnimalCommand;
import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.AnimalCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.AnimalRepository;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.CageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class represents the service implementation for the Animal emtity.
 * It implements the methods to create, update and delete an animal.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

@Service
public class AnimalCommandServiceImpl implements AnimalCommandService {
    private final AnimalRepository animalRepository;
    private final CageRepository cageRepository;

    public AnimalCommandServiceImpl(AnimalRepository animalRepository, CageRepository cageRepository) {
        this.animalRepository = animalRepository;
        this.cageRepository = cageRepository;
    }

    /**
     * Creates an animal in the database
     * @param command the command to create an animal
     * @return the created animal
     */
    @Override
    public Long handle(CreateAnimalCommand command) {
        //check if cage exists
        Cage cage = cageRepository
                .findById(command.cageId()).orElseThrow(()
                        -> new CageNotFoundException(command.cageId()));
        // check if breed is valid
        try {
            Breed.valueOf(command.breed().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid breed");
        }

        var animal = new Animal(command, cage);
        try {
            animalRepository.save(animal);
        } catch (Exception e) {
            throw new AnimalSaveException("Error while saving animal", e);
        }
        return animal.getId();
    }

    /**
     * Updates an animal in the database
     * @param command the command to update an animal
     * @return the updated animal
     */
    @Override
    public Optional<Animal> handle(UpdateAnimalCommand command) {
        // check if breed is valid
        try {
            Breed.valueOf(command.breed().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid breed");
        }

        return animalRepository.findById(command.animalId()).map(animal -> {
            animal.setName(new Name(command.name()));
            animal.setBreed(Breed.valueOf(command.breed().toUpperCase()));
            animal.setGender(new Gender(command.gender()));
            animal.setBirthdate(new Birthdate(command.birthdate()));
            animal.setWeight(new Weight(command.weight()));
            animal.setIsSick(new IsSick(command.isSick()));
            animal.setObservations(new Observations(command.observations()));
            return animalRepository.save(animal);
        });
    }

    /**
     * Deletes an animal from the database
     * @param command the command to delete an animal
     * @return the deleted animal
     */
    @Override
    public Optional<Animal> handle(DeleteAnimalCommand command) {
        if (!animalRepository.existsById(command.animalId())) {
            throw new AnimalNotFoundException(command.animalId());
        }
        var animal = animalRepository.findById(command.animalId());
        animal.ifPresent(animalRepository::delete);
        return animal;
    }
}
