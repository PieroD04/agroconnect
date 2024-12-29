package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.interfaces.rest.resources.CreateResourceResource;

/**
 * This class represents the assembler for the CreateResourceCommand from the CreateResourceResource.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public class CreateResourceCommandFromResourceAssembler {
    public static CreateResourceCommand toCommandFromResource(CreateResourceResource resource){
        return new CreateResourceCommand(
                resource.name(),
                resource.type(),
                resource.quantity(),
                resource.date(),
                resource.observations(),
                resource.breederId()
        );
    }
}
