package com.acme.web.services.management.application.internal.queryservices;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesByTypeAndIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;
import com.acme.web.services.management.domain.services.ResourceQueryService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceQueryServiceImpl implements ResourceQueryService {
    private final ResourceRepository resourceRepository;

    public ResourceQueryServiceImpl(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }

    @Override
    public List<Resource> handle(GetAllResourcesQuery query){
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> handle(GetResourceByIdQuery query){
        return resourceRepository.findById(query.resourceId());
    }

    /**
     * This method is used to get all resources by type and breeder id
     * @param query
     * @return
     */

    @Override
    public List<Resource> handle(GetAllResourcesByTypeAndIdQuery query) {
        return resourceRepository.findAllByResourceTypeAndBreederId(query.type(), query.breederId());
    }

    @Override
    public List<Resource> handle(GetAllResourcesByBreederIdQuery query) {
        return resourceRepository.findAllByBreederId(query.breederId());
    }
}
