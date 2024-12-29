package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

/**
 * CreateCageResource represents the resource to create a cage.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */

public record CreateCageResource(
        @NotNull String name,
        @NotNull Integer size,
        @NotNull String observations,
        @NotNull Long breederId) {
}
