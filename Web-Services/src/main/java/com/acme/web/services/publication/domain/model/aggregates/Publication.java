package com.acme.web.services.publication.domain.model.aggregates;

import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.domain.model.valueobjects.PublicationContent;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;


/**
 * Publication aggregate root
 * It contains the attributes of the publication, the constructor and the getters
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */

@Getter
@Entity
public class Publication extends AuditableAbstractAggregateRoot<Publication> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "title")),
            @AttributeOverride(name = "description", column = @Column(name = "description")),
            @AttributeOverride(name = "image", column = @Column(name = "image"))
    })
    private PublicationContent publicationContent;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public Publication(String title, String description, String image, Date date, Advisor advisor) {
        this.publicationContent = new PublicationContent(title, description, image);
        this.date = date;
        this.advisor = advisor;
    }

    public Publication() {
    }

    public Publication(CreatePublicationCommand command, Advisor advisor) {
        this.publicationContent = new PublicationContent(command.title(), command.description(), command.image());
        this.date = command.date();
        this.advisor = advisor;
    }

    public void updatePublicationContent(String title, String description, String image) {
        this.publicationContent = new PublicationContent(title, description, image);
    }

}
