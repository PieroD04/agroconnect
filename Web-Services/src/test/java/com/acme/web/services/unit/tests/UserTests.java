package com.acme.web.services.unit.tests;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.iam.domain.model.entities.Role;
import com.acme.web.services.iam.domain.model.valueobjects.Roles;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Test
    public void testUserConstructor() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        Role role1 = new Role(Roles.ROLE_USER);
        Role role2 = new Role(Roles.ROLE_ADMIN);
        Set<Role> roles = new HashSet<>(Arrays.asList(role1, role2));

        // Act
        User user = new User(username, password, Arrays.asList(role1, role2));

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertTrue(user.getRoles().containsAll(roles));
    }
    @Test
    public void testUserCreationWithInvalidRole() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, password);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            user.addRole(new Role(Roles.valueOf("ROLE_INVALID")));
        });
    }
}