package com.acme.web.services.publication.interfaces.rest;

import com.acme.web.services.publication.domain.model.commands.DeletePublicationCommand;
import com.acme.web.services.publication.domain.model.queries.GetAllPublicationsQuery;
import com.acme.web.services.publication.domain.model.queries.GetPublicationByIdQuery;
import com.acme.web.services.publication.domain.services.PublicationCommandService;
import com.acme.web.services.publication.domain.services.PublicationQueryService;
import com.acme.web.services.publication.interfaces.rest.resources.CreatePublicationResource;
import com.acme.web.services.publication.interfaces.rest.resources.PublicationResource;
import com.acme.web.services.publication.interfaces.rest.transform.CreatePublicationCommandFromResourceAssembler;
import com.acme.web.services.publication.interfaces.rest.transform.PublicationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Publications Controller class
 * Rest controller for publications management
 * @author Salvador Antonio Salinas Torres - U20221B127
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/publications", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Publications", description = "Publications Management Endpoints")
public class PublicationsController {
    private final PublicationCommandService publicationCommandService;
    private final PublicationQueryService publicationQueryService;

    public PublicationsController(PublicationCommandService publicationCommandService, PublicationQueryService publicationQueryService) {
        this.publicationCommandService = publicationCommandService;
        this.publicationQueryService = publicationQueryService;
    }

    /**
     * Create a new publication
     * @param resource Publication resource
     * @return Publication resource
     */
    @PostMapping
    public ResponseEntity<PublicationResource> createPublication(@RequestBody CreatePublicationResource resource) {
        var createPublicationCommand = CreatePublicationCommandFromResourceAssembler.toCommandFromResource(resource);
        var publicationId = publicationCommandService.handle(createPublicationCommand);
        if (publicationId == 0L) return ResponseEntity.badRequest().build();
        var getPublicationByIdQuery = new GetPublicationByIdQuery(publicationId);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);
        if (publication.isEmpty()) return ResponseEntity.badRequest().build();
        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return new ResponseEntity<>(publicationResource, HttpStatus.CREATED);
    }

    /**
     * Get all publications
     * @return List of publication resources
     */
    @GetMapping
    public ResponseEntity<List<PublicationResource>> getAllPublications() {
        var getAllPublicationsQuery = new GetAllPublicationsQuery();
        var publications = publicationQueryService.handle(getAllPublicationsQuery);
        var publicationResources = publications.stream()
                .map(PublicationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(publicationResources);
    }


    /**
     * Get publication by id
     * @param publicationId
     * @return Publication resource
     */
    @GetMapping("/{publicationId}")
    public ResponseEntity<PublicationResource> getPublicationById(@PathVariable Long publicationId) {
        var getPublicationByIdQuery = new GetPublicationByIdQuery(publicationId);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);
        if (publication.isEmpty()) return ResponseEntity.notFound().build();
        var publicationResource = PublicationResourceFromEntityAssembler.toResourceFromEntity(publication.get());
        return ResponseEntity.ok(publicationResource);
    }

    /**
     * Delete publication by id
     * @param publicationId Publication id
     * @return Success message
     */
    @DeleteMapping("/{publicationId}")
    public ResponseEntity<?> deletePublication(@PathVariable Long publicationId) {
        var getPublicationByIdQuery = new GetPublicationByIdQuery(publicationId);
        var publication = publicationQueryService.handle(getPublicationByIdQuery);
        if (publication.isEmpty()) return ResponseEntity.notFound().build();
        var deletePublicationCommand = new DeletePublicationCommand(publicationId);
        publicationCommandService.handle(deletePublicationCommand);
        return ResponseEntity.ok("Publication with given id successfully deleted");
    }

}
