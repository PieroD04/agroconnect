package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;

public interface AdvisorCommandService {
    Long handle(CreateAdvisorCommand command);
}
