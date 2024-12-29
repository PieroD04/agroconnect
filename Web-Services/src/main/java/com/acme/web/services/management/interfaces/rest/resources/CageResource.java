package com.acme.web.services.management.interfaces.rest.resources;

/**
 * Cage Resource represents the data that is exposed through the REST API for the Cage entity.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
public record CageResource(Long id, String name, Integer size, String observations, Long breederId) {}