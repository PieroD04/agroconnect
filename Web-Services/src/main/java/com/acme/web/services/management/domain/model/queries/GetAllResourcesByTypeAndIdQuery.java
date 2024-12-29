package com.acme.web.services.management.domain.model.queries;

import com.acme.web.services.management.domain.model.valueobjects.ResourceType;

public record GetAllResourcesByTypeAndIdQuery(ResourceType type, Long breederId) {
}
