package com.acme.web.services.appointment.domain.services;

import com.acme.web.services.appointment.domain.model.entities.Review;
import com.acme.web.services.appointment.domain.model.queries.GetAllReviewsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewByIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewsByAppointmentIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetReviewsByAppointmentIdQuery query);
}
