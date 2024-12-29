package com.acme.web.services.publication.domain.services;

import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.domain.model.commands.DeletePublicationCommand;


public interface PublicationCommandService {
    Long handle(CreatePublicationCommand command);
    void handle(DeletePublicationCommand command);
}