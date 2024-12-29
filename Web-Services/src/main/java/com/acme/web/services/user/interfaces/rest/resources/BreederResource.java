package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;

public record BreederResource(Long id, String fullname, String location, LocalDate birthdate, String description, Long userId) {
}
