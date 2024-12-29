package com.acme.web.services.appointment.domain.model.commands;

public record CreateReviewCommand(Long appointmentId, String comment, int rating) {
}
