package com.acme.web.services.user.domain.model.commands;

import java.time.LocalDate;

public record CreateAdvisorCommand(String fullname, String location, LocalDate birthdate, String description, String occupation, int experience, String photo, int rating, Long userId) {
}
