package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateAdvisorResource(String fullname, String location, LocalDate birthdate, String description, String occupation, int experience, String photo, int rating, Long userId) {
}
