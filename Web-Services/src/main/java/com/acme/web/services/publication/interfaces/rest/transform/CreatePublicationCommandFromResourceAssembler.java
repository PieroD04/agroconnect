package com.acme.web.services.publication.interfaces.rest.transform;

import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.interfaces.rest.resources.CreatePublicationResource;

public class CreatePublicationCommandFromResourceAssembler {
    public static CreatePublicationCommand toCommandFromResource(CreatePublicationResource resource) {
        return new CreatePublicationCommand(
                resource.title(),
                resource.description(),
                resource.image(),
                resource.date(),
                resource.advisorId()
        );
    }
}
