package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Expense aggregate root
 * It contains the attributes of the expense, the constructor and the getters
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
@Getter
@Entity
public class Expense extends AuditableAbstractAggregateRoot<Expense> {
    @Setter
    @Embedded
    private Name name;
    @Setter
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
    @Setter
    @Embedded
    private Amount amount;
    @Setter
    @Embedded
    private DateOfCreation date;
    @Setter
    @Embedded
    private Observations observations;


    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    public Expense(){}

    /**
     * Constructor with all attributes as value objects
     * @param name the name of the expense
     * @param type the type of the expense
     * @param amount the amount of the expense
     * @param date the date of the expense
     * @param observations the observations of the expense
     * @param breeder the breeder who made the expense
     */
    public Expense(Name name, ExpenseType type, Amount amount, DateOfCreation date, Observations observations, Breeder breeder) {
        this.name = name;
        this.expenseType = type;
        this.amount = amount;
        this.date = date;
        this.observations = observations;
        this.breeder = breeder;
    }

    /**
     * Constructor with the command
     * @param command the command to create an expense
     * @param breeder the breeder who made the expense
     */
    public Expense(CreateExpenseCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.expenseType = ExpenseType.valueOf(command.type().toUpperCase());
        this.amount = new Amount(command.amount());
        this.date = new DateOfCreation(command.date());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
    }

    //Getters
    public String getName() {
        return name.name();
    }
    public String getType() {
        return this.expenseType != null ? this.expenseType.toString() : "OTROS";
    }
    public Float getAmount() {
        return amount.amount();
    }
    public LocalDate getDate() { return date.date(); }
    public String getObservations() { return observations.observations(); }
}
