package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.interfaces.rest.resources.ResourceResource;

/**
 * This class represents the assembler for the ResourceResource from the Resource entity.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class ResourceResourceFromEntityAssembler {
    public static ResourceResource toResourceFromEntity(Resource entity) {
        return new ResourceResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getQuantity(),
                entity.getDate(),
                entity.getObservations(),
                entity.getBreeder().getId());
    }
}
