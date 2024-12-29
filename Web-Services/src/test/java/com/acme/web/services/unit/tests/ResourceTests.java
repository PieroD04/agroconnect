package com.acme.web.services.unit.tests;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTests {
    @Test
    public void testResourceCreation() {
        Breeder breeder = new Breeder();
        Name name = new Name("Resource1");
        ResourceType type = ResourceType.ALIMENTO;
        Quantity quantity = new Quantity(10);
        DateOfCreation date = new DateOfCreation(LocalDate.now());
        Observations observations = new Observations("Observations");
        Resource resource = new Resource(name, type, quantity, date, observations, breeder);
        assertNotNull(resource);
        assertEquals("Resource1", resource.getName());
        assertEquals(ResourceType.ALIMENTO.toString(), resource.getType());
        assertEquals(10, resource.getQuantity());
        assertEquals("Observations", resource.getObservations());
    }

    @Test
    public void testResourceCreationWithCommand() {
        Breeder breeder = new Breeder();
        Long breederId = breeder.getId();
        CreateResourceCommand command = new CreateResourceCommand("Resource1", "MEDICINA", 10, LocalDate.now(), "Observations", breederId);
        Resource resource = new Resource(command, breeder);
        assertNotNull(resource);
        assertEquals("Resource1", resource.getName());
        assertEquals(ResourceType.MEDICINA.toString(), resource.getType());
        assertEquals(10, resource.getQuantity());
        assertEquals("Observations", resource.getObservations());
    }

    @Test
    public void testResourceCreationWithPositiveQuantity() {
        Breeder breeder = new Breeder();
        Name name = new Name("Resource1");
        ResourceType type = ResourceType.ALIMENTO;
        Quantity quantity = new Quantity(10); // Positive quantity
        DateOfCreation date = new DateOfCreation(LocalDate.now());
        Observations observations = new Observations("Observations");

        assertDoesNotThrow(() -> {
            Resource resource = new Resource(name, type, quantity, date, observations, breeder);
        });
    }

}
