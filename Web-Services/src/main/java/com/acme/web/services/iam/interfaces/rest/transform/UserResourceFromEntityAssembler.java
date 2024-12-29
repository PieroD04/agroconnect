package com.acme.web.services.iam.interfaces.rest.transform;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.iam.domain.model.entities.Role;
import com.acme.web.services.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
