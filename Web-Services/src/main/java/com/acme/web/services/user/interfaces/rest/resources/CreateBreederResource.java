package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateBreederResource(String fullname, String location, LocalDate birthdate, String description, Long userId) {
}
