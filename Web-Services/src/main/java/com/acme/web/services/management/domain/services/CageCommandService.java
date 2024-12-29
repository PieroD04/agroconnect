package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.domain.model.commands.DeleteCageCommand;
import com.acme.web.services.management.domain.model.commands.UpdateCageCommand;

import java.util.Optional;

/**
 * Cage command service interface.
 * It defines the methods that must be implemented by the CageCommandService class.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface CageCommandService {
    Long handle(CreateCageCommand command);
    Optional<Cage> handle(UpdateCageCommand command);
    Optional<Cage> handle(DeleteCageCommand command);
}
