package com.acme.web.services.management.application.internal.queryservices;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.queries.GetAllCagesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllCagesQuery;
import com.acme.web.services.management.domain.model.queries.GetCageByIdQuery;
import com.acme.web.services.management.domain.services.CageQueryService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.CageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is used to implement the CageQueryService interface
 */
@Service
public class CageQueryServiceImpl implements CageQueryService {
    private final CageRepository cageRepository;

    public CageQueryServiceImpl(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }

    @Override
    public List<Cage> handle(GetAllCagesQuery query) {
        return this.cageRepository.findAll();
    }

    @Override
    public Optional<Cage> handle(GetCageByIdQuery query) {
        return  this.cageRepository.findById(query.cageId());
    }

    /**
     * This method is used to get all cages by breeder id
     * @param query
     * @return
     */
    @Override
    public List<Cage> handle(GetAllCagesByBreederIdQuery query) {
        return this.cageRepository.findAllByBreederId(query.breederId());
    }
}
