package com.acme.web.services.unit.tests;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CageTests {
    // The following test is an example of the creation of a Cage object
    @Test
    public void testCageCreation() {
        Breeder breeder = new Breeder();
        Cage cage = new Cage("Cage1", 10, "Observations", breeder);
        assertNotNull(cage);
        assertEquals("Cage1", cage.name());
        assertEquals(10, cage.size());
        assertEquals("Observations", cage.observations());
    }

    // The following test is an example of the creation of a Cage object with a command
    @Test
    public void testCageCreationWithCommand() {
        Breeder breeder = new Breeder();

        // An hipothetically generated id
        Long id = 5L; //The L is for long, so the number is treated as a long literal

        CreateCageCommand command = new CreateCageCommand("Cage1", 10, "Observations", id);
        Cage cage = new Cage(command, breeder);
        assertNotNull(cage);
        assertEquals("Cage1", cage.name());
        assertEquals(10, cage.size());
        assertEquals("Observations", cage.observations());
    }

    @Test
    public void testCageCreationWithPositiveSize() {
        Breeder breeder = new Breeder();
        String name = "Cage1";
        int size = 10; // Positive size
        String observations = "Observations";

        assertDoesNotThrow(() -> {
            Cage cage = new Cage(name, size, observations, breeder);
        });
    }

}
