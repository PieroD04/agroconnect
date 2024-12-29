package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateAnimalCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateAnimalResource;

/**
 * This class represents the assembler for the UpdateAnimalCommand from the UpdateAnimalResource.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class UpdateAnimalCommandFromResourceAssembler {
    public static UpdateAnimalCommand toCommandFromResource(Long animalId, UpdateAnimalResource resource) {
        return new UpdateAnimalCommand(
                animalId, resource.name(), resource.breed(), resource.gender(),
                resource.birthdate(), resource.weight(), resource.isSick(),
                resource.observations(), resource.cageId()
        );
    }
}
