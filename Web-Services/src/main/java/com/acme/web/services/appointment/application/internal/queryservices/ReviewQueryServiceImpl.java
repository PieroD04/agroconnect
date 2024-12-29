package com.acme.web.services.appointment.application.internal.queryservices;

import com.acme.web.services.appointment.domain.model.entities.Review;
import com.acme.web.services.appointment.domain.model.queries.GetAllReviewsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewByIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewsByAppointmentIdQuery;
import com.acme.web.services.appointment.domain.services.ReviewQueryService;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the service implementation for the ReviewQueryService.
 * @author Andre Gabriel Valverde Mozo -U202218899
 * @version 1.0
 */
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.reviewId());
    }

    @Override
    public List<Review> handle(GetReviewsByAppointmentIdQuery query) {
        return reviewRepository.findByAppointmentId(query.appointmentId());
    }
}
