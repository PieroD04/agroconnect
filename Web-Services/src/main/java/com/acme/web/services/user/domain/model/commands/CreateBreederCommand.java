package com.acme.web.services.user.domain.model.commands;

import java.time.LocalDate;

public record CreateBreederCommand(String fullname, String location, LocalDate birthdate, String description, Long userId){
}
