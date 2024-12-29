package com.acme.web.services.iam.interfaces.rest.transform;

import com.acme.web.services.iam.domain.model.entities.Role;
import com.acme.web.services.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}