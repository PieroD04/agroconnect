package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesByTypeAndIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Resource query service interface.
 * This interface represents the service for handling resource queries.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public interface ResourceQueryService {
    List<Resource> handle(GetAllResourcesQuery query);
    Optional<Resource> handle(GetResourceByIdQuery query);
    List<Resource> handle(GetAllResourcesByTypeAndIdQuery query);
    List<Resource> handle(GetAllResourcesByBreederIdQuery query);
}
