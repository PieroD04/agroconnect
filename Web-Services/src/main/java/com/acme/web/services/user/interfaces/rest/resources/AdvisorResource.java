package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;

public record AdvisorResource(Long id, String fullname, String location, LocalDate birthdate, String description, String occupation, int experience, String photo, int rating, Long userId) {
}
