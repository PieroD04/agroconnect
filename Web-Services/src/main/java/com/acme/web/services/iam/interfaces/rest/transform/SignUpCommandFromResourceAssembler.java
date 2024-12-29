package com.acme.web.services.iam.interfaces.rest.transform;

import com.acme.web.services.iam.domain.model.commands.SignUpCommand;
import com.acme.web.services.iam.domain.model.entities.Role;
import com.acme.web.services.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.username(), resource.password(), roles);
    }
}