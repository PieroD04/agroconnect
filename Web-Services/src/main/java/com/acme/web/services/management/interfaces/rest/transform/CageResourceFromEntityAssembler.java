package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.interfaces.rest.resources.CageResource;

/**
 * This class represents the assembler for the CageResource from the Cage entity.
 * It is used to convert a Cage entity to a CageResource object.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public class CageResourceFromEntityAssembler {
    public static CageResource toResourceFromEntity(Cage entity) {
        return new CageResource(
                entity.getId(),
                entity.name(),
                entity.size(),
                entity.observations(),
                entity.getBreeder().getId());
    }
}
