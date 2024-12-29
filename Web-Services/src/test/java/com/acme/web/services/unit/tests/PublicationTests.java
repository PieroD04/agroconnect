package com.acme.web.services.unit.tests;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PublicationTests {
    @Test
    public void testDefaultPublicationCreation() {
        Advisor advisor = new Advisor();
        Publication publication = new Publication("title", "description", "image", new Date(), advisor);
        assertNotNull(publication);
        assertNotNull(publication.getDate());
        assertNotNull(publication.getAdvisor());
    }
    @Test
    public void testPublicationContentUpdate() {
        Advisor advisor = new Advisor(); // Asume que Advisor tiene un constructor sin argumentos
        Publication publication = new Publication("title", "description", "image", new Date(), advisor);
        publication.updatePublicationContent("new title", "new description", "new image");
        assertEquals("new title", publication.getPublicationContent().title());
        assertEquals("new description", publication.getPublicationContent().description());
        assertEquals("new image", publication.getPublicationContent().image());
    }
}
