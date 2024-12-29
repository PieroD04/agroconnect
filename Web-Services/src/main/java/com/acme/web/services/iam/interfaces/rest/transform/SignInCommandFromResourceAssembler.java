package com.acme.web.services.iam.interfaces.rest.transform;

import com.acme.web.services.iam.domain.model.commands.SignInCommand;
import com.acme.web.services.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}