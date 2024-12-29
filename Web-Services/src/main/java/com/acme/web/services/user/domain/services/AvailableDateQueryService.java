package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.domain.model.queries.GetAllAvailableDatesQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDateByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDatesByAdvisorIdQuery;

import java.util.List;
import java.util.Optional;

public interface AvailableDateQueryService {
    List<AvailableDate> handle(GetAllAvailableDatesQuery query);
    Optional<AvailableDate> handle(GetAvailableDateByIdQuery query);
    List<AvailableDate> handle(GetAvailableDatesByAdvisorIdQuery query);
}
