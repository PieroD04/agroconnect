package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.interfaces.rest.resources.BreederResource;

public class BreederResourceFromEntityAssembler {
    public static BreederResource toResourceFromEntity(Breeder entity){
        return new BreederResource(
                entity.getId(),
                entity.getFullname(),
                entity.getLocation(),
                entity.getBirthdate(),
                entity.getDescription(),
                entity.getUserId()
        );
    }
}
