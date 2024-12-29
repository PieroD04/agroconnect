package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Resource aggregate root
 * It contains the attributes of the resource, the constructor and the getters
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@Getter
@Entity
public class Resource extends AuditableAbstractAggregateRoot<Resource> {
    @Setter
    @Embedded
    private Name name;
    @Setter
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    @Setter
    @Embedded
    private Quantity quantity;
    @Setter
    @Embedded
    private DateOfCreation date;
    @Setter
    @Embedded
    private Observations observations;


    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    public Resource(){}

    /**
     * Constructor with attributes as value objects
     * @param name name of the resource
     * @param type type of the resource
     * @param quantity quantity of the resource
     * @param date date of creation of the resource
     * @param observations observations of the resource
     * @param breeder breeder who owns the resource
     */
    public Resource(Name name, ResourceType type, Quantity quantity, DateOfCreation date, Observations observations, Breeder breeder) {
        this.name = name;
        this.resourceType = type;
        this.quantity = quantity;
        this.date = date;
        this.observations = observations;
        this.breeder = breeder;
    }

    /**
     * Constructor with command
     * @param command command to create a resource
     * @param breeder breeder who owns the resource
     */
    public Resource(CreateResourceCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.resourceType = ResourceType.valueOf(command.type().toUpperCase());
        this.quantity = new Quantity(command.quantity());
        this.date = new DateOfCreation(command.date());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
    }

    //Getters
    public String getName() {
        return name.name();
    }

    public String getType() {
        return this.resourceType != null ? this.resourceType.toString() : "OTROS";
    }

    public Integer getQuantity() {
        return quantity.quantity();
    }

    public LocalDate getDate() {

        return date.date();
    }

    public String getObservations() {

        return observations.observations();
    }
}
