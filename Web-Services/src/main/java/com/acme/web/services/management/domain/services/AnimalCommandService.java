package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.domain.model.commands.DeleteAnimalCommand;
import com.acme.web.services.management.domain.model.commands.UpdateAnimalCommand;
import com.acme.web.services.management.domain.model.entities.Animal;

import java.util.Optional;

/**
 * Interface for the AnimalCommandService.
 * It defines the methods that must be implemented by the service.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface AnimalCommandService {
    Long handle(CreateAnimalCommand command);
    Optional<Animal> handle(UpdateAnimalCommand command);
    Optional<Animal> handle(DeleteAnimalCommand command);
}
