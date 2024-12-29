package com.acme.web.services.iam.domain.model.queries;


import com.acme.web.services.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}