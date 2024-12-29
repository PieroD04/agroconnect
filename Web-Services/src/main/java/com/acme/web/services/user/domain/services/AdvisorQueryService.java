package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.queries.GetAdvisorByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAllAdvisorsQuery;

import java.util.List;
import java.util.Optional;

public interface AdvisorQueryService {
    List<Advisor> handle(GetAllAdvisorsQuery query);
    Optional<Advisor> handle(GetAdvisorByIdQuery query);
    Long getUserIdByAdvisorId(Long advisorId);
}