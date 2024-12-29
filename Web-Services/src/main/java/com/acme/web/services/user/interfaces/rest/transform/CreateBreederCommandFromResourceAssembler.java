package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;
import com.acme.web.services.user.interfaces.rest.resources.CreateBreederResource;

public class CreateBreederCommandFromResourceAssembler {
    public static CreateBreederCommand toCommandFromResource(CreateBreederResource resource){
        return new CreateBreederCommand(
                resource.fullname(),
                resource.location(),
                resource.birthdate(),
                resource.description(),
                resource.userId()
        );
    }
}
