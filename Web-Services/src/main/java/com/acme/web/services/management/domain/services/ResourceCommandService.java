package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;

import java.util.Optional;

/**
 * Resource command service interface.
 * It defines the methods that must be implemented by the ResourceCommandService class.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface ResourceCommandService {
    Long handle(CreateResourceCommand command);
    Optional<Resource> handle(UpdateResourceCommand command);
    Optional<Resource> handle(DeleteResourceCommand command);
}
