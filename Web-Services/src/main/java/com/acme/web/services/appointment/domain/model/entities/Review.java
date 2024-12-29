package com.acme.web.services.appointment.domain.model.entities;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/**
 * This class represents the Review entity.
 * It contains the attributes of the Review.
 * It contains the constructor of the Review.
 * It contains the methods to get the review comment and rating.
 * @author Andre Gabriel Valverde Mozo -U202218899
 * @version 1.0
 */

@Getter
@Entity
public class Review extends AuditableAbstractAggregateRoot<Review>{

    @Getter
    @Column(name="comment", columnDefinition="TEXT")
    private String comment;

    @Getter
    @Column(name="rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public Review(){
        this.comment = "Sin Comentarios";
        this.rating = 0;
    }

    /**
     * Constructor of the Review entity.
     * @param command the command to create a review
     */
    public Review(CreateReviewCommand command){
        this.comment = command.comment();
        this.rating = command.rating();
    }

    /**
     * Constructor of the Review entity.
     * @param appointment the appointment to create a review
     * @param command the command to create a review
     */
    public Review(Appointment appointment, CreateReviewCommand command){
        this.comment = command.comment();
        this.rating = command.rating();
        this.appointment = appointment;
    }

    //getters
    public String getReviewComment(){
        return this.comment;
    }
    public int getReviewRating(){
        return this.rating;
    }
}
