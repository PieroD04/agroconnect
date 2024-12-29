package com.acme.web.services.publication.interfaces.rest.transform;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.interfaces.rest.resources.PublicationResource;

public class PublicationResourceFromEntityAssembler {
    public static PublicationResource toResourceFromEntity(Publication entity) {
        return new PublicationResource(
                entity.getId(),
                entity.getPublicationContent().title(),
                entity.getPublicationContent().description(),
                entity.getPublicationContent().image(),
                entity.getDate(),
                entity.getAdvisor().getId());
    }
}
