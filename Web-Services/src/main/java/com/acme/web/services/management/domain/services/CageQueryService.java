package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.queries.GetAllCagesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllCagesQuery;
import com.acme.web.services.management.domain.model.queries.GetCageByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Cage query service interface.
 * It defines the methods that must be implemented by the Cage query service.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public interface CageQueryService {
    List<Cage> handle(GetAllCagesQuery query);
    Optional<Cage> handle(GetCageByIdQuery query);
    List<Cage> handle(GetAllCagesByBreederIdQuery query);
}
