package com.acme.web.services.publication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PublicationContent(String title, String description, String image) {
    public PublicationContent() {
        this(null, null, null);
    }

    public PublicationContent {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
    }
}
