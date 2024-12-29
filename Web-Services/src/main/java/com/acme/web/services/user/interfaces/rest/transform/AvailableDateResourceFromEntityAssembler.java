package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.interfaces.rest.resources.AvailableDateResource;

public class AvailableDateResourceFromEntityAssembler {
    public static AvailableDateResource toResourceFromEntity(AvailableDate entity){
        return new AvailableDateResource(
                entity.getId(),
                entity.getAdvisor().getId(),
                entity.getDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus()
        );
    }
}
