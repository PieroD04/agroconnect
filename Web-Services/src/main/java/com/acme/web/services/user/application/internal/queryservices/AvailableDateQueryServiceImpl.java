package com.acme.web.services.user.application.internal.queryservices;

import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.domain.model.queries.GetAllAvailableDatesQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDateByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDatesByAdvisorIdQuery;
import com.acme.web.services.user.domain.services.AvailableDateQueryService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AvailableDateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateQueryServiceImpl implements AvailableDateQueryService {
    private final AvailableDateRepository availableDateRepository;

    public AvailableDateQueryServiceImpl(AvailableDateRepository availableDateRepository){
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public List<AvailableDate> handle(GetAllAvailableDatesQuery query){
        return availableDateRepository.findAll();
    }

    @Override
    public Optional<AvailableDate> handle(GetAvailableDateByIdQuery query){
        return availableDateRepository.findById(query.availableDateId());
    }

    @Override
    public List<AvailableDate> handle(GetAvailableDatesByAdvisorIdQuery query){
        return availableDateRepository.findByAdvisorId(query.advisorId());
    }

}
