package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;
    @NotNull
    private String text;
    @NotNull
    private Date date;

    private String meetingUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(CreateNotificationCommand command, User user) {
        this.type = command.type();
        this.text = command.text();
        this.date = command.date();
        this.meetingUrl = command.meetingUrl();
        this.user = user;

    }

    public Notification() {}
}
