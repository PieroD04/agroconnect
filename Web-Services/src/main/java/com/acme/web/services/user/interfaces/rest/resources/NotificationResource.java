package com.acme.web.services.user.interfaces.rest.resources;

import java.util.Date;

public record NotificationResource(Long id, String type, String text, Date date, Long userId, String meetingUrl) {
}
