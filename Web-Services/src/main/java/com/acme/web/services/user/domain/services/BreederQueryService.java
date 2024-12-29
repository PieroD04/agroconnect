package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.domain.model.queries.GetAllBreedersQuery;
import com.acme.web.services.user.domain.model.queries.GetBreederByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BreederQueryService {
    List<Breeder> handle(GetAllBreedersQuery query);
    Optional<Breeder> handle(GetBreederByIdQuery query);
    Long getUserIdByBreederId(Long breederId);
}
