package com.acme.web.services.iam.domain.model.commands;

public record SignInCommand(String username, String password) {
}