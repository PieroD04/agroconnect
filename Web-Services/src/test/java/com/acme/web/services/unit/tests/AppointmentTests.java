package com.acme.web.services.unit.tests;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.valueObjects.Status;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppointmentTests {

    //This test helps to verify that the default appointment creation is working as expected.
    @Test
    public void testDefaultAppointmentCreation() {
        Appointment appointment = new Appointment();
        assertNotNull(appointment); //Not null
        assertEquals(Status.PENDIENTE, appointment.getStatus());
    }

    //This test helps to verify that the appointment creation with specific status is working as expected.
    @Test
    public void testAppointmentCreationWithSpecificStatus() {
        //Appointment appointment = new Appointment(new Date(), "TERMINAD"); // TEST FAIL
        Appointment appointment = new Appointment(new Date(), "TERMINADO");
        assertNotNull(appointment); //Not null
        assertEquals(Status.TERMINADO, appointment.getStatus());
    }

}
