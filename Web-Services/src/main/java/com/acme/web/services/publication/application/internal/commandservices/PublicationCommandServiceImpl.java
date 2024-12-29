package com.acme.web.services.publication.application.internal.commandservices;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.domain.model.commands.DeletePublicationCommand;
import com.acme.web.services.publication.domain.services.PublicationCommandService;
import com.acme.web.services.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import org.springframework.stereotype.Service;

/**
 * This class represents the service implementation for the PublicationCommandService.
 * It contains the methods to handle the commands related to publications.
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */

@Service
public class PublicationCommandServiceImpl implements PublicationCommandService {
    private final PublicationRepository publicationRepository;
    private final AdvisorRepository advisorRepository;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository, AdvisorRepository advisorRepository) {
        this.publicationRepository = publicationRepository;
        this.advisorRepository = advisorRepository;
    }

    @Override
    public Long handle(CreatePublicationCommand command) {
        var advisor = advisorRepository.findById(command.advisorId());
        if (advisor.isEmpty()) {
            throw new IllegalArgumentException("Advisor does not exist");
        }
        var publication = new Publication(command, advisor.get());
        try {
            publicationRepository.save(publication);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error while saving publication: " + e.getMessage());
        }
        return publication.getId();
    }

    @Override
    public void handle(DeletePublicationCommand command) {
        if (!publicationRepository.existsById(command.publicationId())) {
            throw new IllegalArgumentException("Publication does not exist");
        }
        try {
            publicationRepository.deleteById(command.publicationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting publication: " + e.getMessage());
        }
    }

}
