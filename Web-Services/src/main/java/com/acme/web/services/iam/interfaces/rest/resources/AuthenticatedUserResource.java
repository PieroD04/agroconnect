package com.acme.web.services.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}