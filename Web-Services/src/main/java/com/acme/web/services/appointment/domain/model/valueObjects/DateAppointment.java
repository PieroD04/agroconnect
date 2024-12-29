package com.acme.web.services.appointment.domain.model.valueObjects;

import jakarta.persistence.Embeddable;


import java.util.Date;

@Embeddable
public record DateAppointment(Date date) {
    public DateAppointment(){this(null);}
}
