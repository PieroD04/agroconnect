package com.acme.web.services.appointment.domain.model.aggregates;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.valueObjects.*;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/**
 * This class represents the Appointment aggregate.
 * It contains the attributes of the Appointment.
 * It contains the constructor of the Appointment.
 * It contains the methods to get the appointment date.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    @Embedded
    @Column(name="date")
    private final DateAppointment date;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    /**
     * This is the default constructor of the Appointment.
     */
    public Appointment(){
        this.date = new DateAppointment();
        this.status = Status.PENDIENTE; // Default status
    }

    public Appointment(Date date, String status){
        this.date = new DateAppointment(date);
        this.status = Status.valueOf(status.toUpperCase());
    }

    /**
     * This is the constructor of the Appointment.
     * @param command the command to create an appointment
     */
    public Appointment(CreateAppointmentCommand command){
        this.date = new DateAppointment(command.date());
        this.status = Status.valueOf(command.status().toUpperCase());
    }

    /**
     * This is the constructor of the Appointment.
     * @param breeder the breeder of the appointment
     * @param advisor the advisor of the appointment
     * @param command the command to create an appointment
     */
    public Appointment(Breeder breeder, Advisor advisor, CreateAppointmentCommand command){
        this.date = new DateAppointment(command.date());
        this.status = Status.valueOf(command.status().toUpperCase());
        this.breeder = breeder;
        this.advisor = advisor;
    }

    // Getters
    public Date getAppointmentDate(){
        return this.date.date();
    }


}
