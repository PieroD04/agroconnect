package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.exceptions.ResourceNameAlreadyExistsException;
import com.acme.web.services.management.domain.exceptions.ResourceSaveException;
import com.acme.web.services.user.domain.exceptions.BreederNotFoundException;
import com.acme.web.services.management.domain.exceptions.ResourceNotFoundException;
import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ResourceRepository;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class represents the service implementation for the Resource aggregate.
 * It implements the methods to create, update and delete a resource.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@Service
public class ResourceCommandServiceImpl implements ResourceCommandService {
    private final ResourceRepository resourceRepository;
    private final BreederRepository breederRepository;

    public ResourceCommandServiceImpl(ResourceRepository resourceRepository, BreederRepository breederRepository){
        this.resourceRepository = resourceRepository;
        this.breederRepository = breederRepository;
    }

    /**
     * Creates a resource in the database
     * @param command the command to create a resource
     * @return the id of the created resource
     */

    @Override
    public Long handle(CreateResourceCommand command){
        //check if breeder exists
        Breeder breeder = breederRepository
                .findById(command.breederId()).orElseThrow(()
                        -> new BreederNotFoundException(command.breederId()));

        //check if type is valid
        try {
            ResourceType.valueOf(command.type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid resource type");
        }
        //check if name already exists
        Name name = new Name(command.name());
        if(resourceRepository.existsByName(name)){
            throw new ResourceNameAlreadyExistsException(command.name());
        }
        //create and save the resource
        Resource resource = new Resource(command, breeder);
        try {
            resourceRepository.save(resource);
        } catch (Exception e) {
            throw new ResourceSaveException("Error while saving resource", e);
        }
        return resource.getId();
    }

    /**
     * Updates a resource in the database
     * @param command the command to update a resource
     * @return the updated resource
     */
    @Override
    public Optional<Resource> handle(UpdateResourceCommand command) {
        //check if type is valid
        try {
            ResourceType.valueOf(command.type().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid resource type");
        }
        return resourceRepository.findById(command.resourceId()).map(resource -> {
            if (!resource.getName().equals(command.name()) && resourceRepository.existsByName(new Name(command.name()))) {
                throw new ResourceNameAlreadyExistsException(command.name());
            }

            // Actualizar el recurso con los nuevos valores
            resource.setName(new Name(command.name()));
            resource.setResourceType(ResourceType.valueOf(command.type().toUpperCase()));
            resource.setQuantity(new Quantity(command.quantity()));
            resource.setDate(new DateOfCreation(command.date()));
            resource.setObservations(new Observations(command.observations()));

            return resourceRepository.save(resource);
        });
    }

    /**
     * Deletes a resource in the database
     * @param command the command to delete a resource
     * @return the deleted resource
     */
    @Override
    public Optional<Resource> handle(DeleteResourceCommand command) {
        if (!resourceRepository.existsById(command.resourceId())) {
            throw new ResourceNotFoundException(command.resourceId());
        }
        var resource = resourceRepository.findById(command.resourceId());
        resource.ifPresent(resourceRepository::delete);
        return resource;
    }
}
