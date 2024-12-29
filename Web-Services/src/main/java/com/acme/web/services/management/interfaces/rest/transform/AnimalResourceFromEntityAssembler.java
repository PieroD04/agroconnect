package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.interfaces.rest.resources.AnimalResource;

/**
 * This class represents the AnimalResourceFromEntityAssembler.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public class AnimalResourceFromEntityAssembler {
    public static AnimalResource toResourceFromEntity(Animal entity) {
        return new AnimalResource(
                entity.getId(),
                entity.name(),
                entity.breed(),
                entity.gender(),
                entity.birthdate(),
                entity.weight(),
                entity.isSick(),
                entity.observations(),
                entity.getCage().getId());
    }
}
