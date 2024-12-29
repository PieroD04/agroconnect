package com.acme.web.services.unit.tests;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AdvisorTests {
    @Test
    public void testDefaultAdvisorCreation() {
        User user = new User(); // Asume que User tiene un constructor sin argumentos
        CreateAdvisorCommand command = new CreateAdvisorCommand("fullname", "location", LocalDate.now().minusYears(30), "description", "occupation", 10, "photo", 4, user.getId());
        Advisor advisor = new Advisor(command, user);
        assertNotNull(advisor);
        assertEquals("fullname", advisor.getFullname());
        assertEquals("location", advisor.getLocation());
        assertEquals("description", advisor.getDescription());
        assertEquals("occupation", advisor.getOccupation());
        assertEquals(10, advisor.getExperience());
        assertEquals("photo", advisor.getPhoto());
        assertEquals(4, advisor.getRating());
    }
    @Test
    public void testAdvisorCreationWithNullUser() {
        // Arrange
        CreateAdvisorCommand command = new CreateAdvisorCommand("fullname", "location", LocalDate.now().minusYears(30), "description", "occupation", 10, "photo", 4, null);
        // Act
        Advisor advisor = new Advisor(command, null);
        // Assert
        assertNull(advisor.getUser());
    }

}