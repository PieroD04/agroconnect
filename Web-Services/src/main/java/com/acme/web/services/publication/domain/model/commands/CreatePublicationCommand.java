package com.acme.web.services.publication.domain.model.commands;

import java.util.Date;

public record CreatePublicationCommand(String title, String description, String image, Date date, Long advisorId) {
}
