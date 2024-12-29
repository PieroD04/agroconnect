package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;

public interface BreederCommandService {
    Long handle(CreateBreederCommand command);

}
