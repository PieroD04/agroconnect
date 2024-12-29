package com.acme.web.services.publication.domain.services;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.queries.GetAllPublicationsByAdvisorIdQuery;
import com.acme.web.services.publication.domain.model.queries.GetAllPublicationsQuery;
import com.acme.web.services.publication.domain.model.queries.GetPublicationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PublicationQueryService {
    List<Publication> handle(GetAllPublicationsQuery query);
    Optional<Publication> handle(GetPublicationByIdQuery query);
    List<Publication> handle(GetAllPublicationsByAdvisorIdQuery query);
}
