package com.acme.web.services.iam.interfaces.rest.transform;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}