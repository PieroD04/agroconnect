package com.acme.web.services.appointment.interfaces.rest.resources;

public record CreateReviewResource(Long appointmentId, String comment, Integer rating) {
}
