package com.acme.web.services.user.domain.model.aggregates;

import com.acme.web.services.iam.domain.model.aggregates.User;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
public class Advisor extends AuditableAbstractAggregateRoot<Advisor> {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
    @NotNull
    private String occupation;
    @NotNull
    @Min(1)
    @Max(70)
    private int experience;
    @NotNull
    private String photo;
    @NotNull
    @Min(0)
    @Max(5)
    @Setter
    private int rating;

    public Advisor(CreateAdvisorCommand command, User user) {
        //check if string fields are in blank
        if (command.fullname().isBlank() || command.location().isBlank() || command.description().isBlank() || command.occupation().isBlank() || command.photo().isBlank()) {
            throw new IllegalArgumentException("No field can be blank");
        }
        this.fullname = command.fullname();
        this.location = command.location();
        this.birthdate = command.birthdate();
        this.description = command.description();
        this.occupation = command.occupation();
        this.experience = command.experience();
        this.photo = command.photo();
        this.rating = command.rating();
        this.user = user;
    }

    public Advisor() {}

    public Long getUserId() {
        return this.user.getId();
    }
}
