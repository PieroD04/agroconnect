package com.acme.web.services.iam.domain.services;

import com.acme.web.services.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}