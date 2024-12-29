package com.acme.web.services.unit.tests;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BreederTests {

    @Test
    public void testBreederConstructor() {
        // Arrange
        User user = new User();
        CreateBreederCommand command = new CreateBreederCommand("fullname", "location", LocalDate.now().minusYears(30), "description", user.getId());

        // Act
        Breeder breeder = new Breeder(command, user);

        // Assert
        assertEquals("fullname", breeder.getFullname());
        assertEquals("location", breeder.getLocation());
        assertEquals(command.birthdate(), breeder.getBirthdate());
        assertEquals("description", breeder.getDescription());
        assertEquals(user, breeder.getUser());
    }

    @Test
    public void testBreederCreationWithNullUser() {
        // Arrange
        CreateBreederCommand command = new CreateBreederCommand("fullname", "location", LocalDate.now().minusYears(30), "description", null);
        // Act
        Breeder breeder = new Breeder(command, null);
        // Assert
        assertNull(breeder.getUser());
    }
}