package com.acme.web.services.appointment.application.internal.commandservices;


import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import com.acme.web.services.appointment.domain.model.entities.Review;
import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;
import com.acme.web.services.appointment.domain.services.ReviewCommandService;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

/**
 * This class represents the service implementation for the ReviewCommandService.
 * @Author Andre Gabriel Valverde Mozo -U202218899
 * @Version 1.0
 */

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, AppointmentRepository appointmentRepository) {
        this.reviewRepository = reviewRepository;
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Creates a review in the database
     * @param command the command to create a review
     * @return the created review
     */
    @Override
    public Long handle(CreateReviewCommand command) {
        var appointment = appointmentRepository.findById(command.appointmentId());

        if (appointment.isEmpty()) {
            throw new IllegalArgumentException("Appointment does not exist");
        }

        var review = new Review(appointment.get(), command);
        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving review: " + e.getMessage());
        }
        return review.getId();
    }
}
