package com.acme.web.services.user.domain.model.aggregates;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Breeder extends AuditableAbstractAggregateRoot<Breeder> {
    @NotNull
    private String fullname;
    @NotNull
    private String location;
    @Past
    @NotNull
    private LocalDate birthdate;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Breeder(CreateBreederCommand command, User user) {
        //check if string fields are in blank
        if (command.fullname().isBlank() || command.location().isBlank()) {
            throw new IllegalArgumentException("No field can be blank");
        }
        this.fullname = command.fullname();
        this.location = command.location();
        this.birthdate = command.birthdate();
        this.description = command.description();
        this.user = user;
    }

    public Breeder() {}

    public Long getUserId() {
        return this.user.getId();
    }
}
