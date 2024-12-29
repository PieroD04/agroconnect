package com.acme.web.services.user.domain.model.commands;

import java.util.Date;

public record CreateNotificationCommand(String type, String text, Date date, Long userId, String meetingUrl) {
}
