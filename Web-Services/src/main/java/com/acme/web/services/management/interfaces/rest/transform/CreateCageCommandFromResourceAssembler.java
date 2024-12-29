package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.interfaces.rest.resources.CreateCageResource;

/**
 * This class represents the assembler for the CreateCageCommand from the CreateCageResource.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class CreateCageCommandFromResourceAssembler {
    public static CreateCageCommand toCommandFromResource(CreateCageResource resource){
        return new CreateCageCommand(
                resource.name(),
                resource.size(),
                resource.observations(),
                resource.breederId()
        );
    }
}
