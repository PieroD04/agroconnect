package com.acme.web.services.appointment.interfaces.rest.resources;

public record ReviewResource(Long reviewId, Long appointmentId, String comment, Integer rating) {
}
