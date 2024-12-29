package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import com.acme.web.services.user.interfaces.rest.resources.CreateAdvisorResource;

public class CreateAdvisorCommandFromResourceAssembler {
    public static CreateAdvisorCommand toCommandFromResource(CreateAdvisorResource resource){
        return new CreateAdvisorCommand(
                resource.fullname(),
                resource.location(),
                resource.birthdate(),
                resource.description(),
                resource.occupation(),
                resource.experience(),
                resource.photo(),
                resource.rating(),
                resource.userId()
        );
    }
}
