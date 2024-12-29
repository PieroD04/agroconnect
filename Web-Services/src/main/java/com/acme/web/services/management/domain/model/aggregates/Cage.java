package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.Observations;
import com.acme.web.services.management.domain.model.valueobjects.Size;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * cage aggregate root
 * It contains the attributes of the cage, the constructor and the getters
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@Getter
@Entity
public class Cage extends AuditableAbstractAggregateRoot<Cage> {
    @Setter
    @Embedded
    private Name name;

    @Setter
    @Embedded
    private Size size;

    @Setter
    @Embedded
    private Observations observations;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    @OneToMany(mappedBy = "cage", cascade = CascadeType.ALL)
    private List<Animal> animals;

    /**
     * Constructor
     * @param name name of the cage
     * @param size size of the cage
     * @param observations observations of the cage
     * @param breeder breeder who owns the cage
     */
    public Cage(String name, Integer size, String observations, Breeder breeder){
        this.name = new Name(name);
        this.size = new Size(size);
        this.observations = new Observations(observations);
        this.breeder = breeder;
        this.animals = new ArrayList<>();
    }

    /**
     * Constructor with attributes as value objects
     * @param name name of the cage
     * @param size size of the cage
     * @param observations observations of the cage
     * @param breeder breeder who owns the cage
     */
    public Cage(Name name, Size size, Observations observations, Breeder breeder) {
        this.name = name;
        this.size = size;
        this.observations = observations;
        this.breeder = breeder;
        this.animals = new ArrayList<>();
    }

    /**
     * Constructor
     * @param command
     * @param breeder
     */
    public Cage(CreateCageCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.size = new Size(command.size());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
        this.animals = new ArrayList<>();
    }

    public Cage() {}

    public String name() {
        return this.name.name();
    }

    public Integer size() {
        return this.size.size();
    }

    public String observations() {
        return this.observations.observations();
    }
}