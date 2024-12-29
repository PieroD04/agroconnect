package com.acme.web.services.management.interfaces.rest;

import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.domain.services.ResourceQueryService;
import com.acme.web.services.management.interfaces.rest.resources.CreateResourceResource;
import com.acme.web.services.management.interfaces.rest.resources.UpdateResourceResource;
import com.acme.web.services.management.interfaces.rest.transform.ResourceResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.resources.ResourceResource;
import com.acme.web.services.management.interfaces.rest.transform.CreateResourceCommandFromResourceAssembler;
import com.acme.web.services.management.interfaces.rest.transform.UpdateResourceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

/**
 * Resources Controller
 * This class represents the REST controller for the Resources.
 * It contains the endpoints for the Resource Management.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/resources", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Resources", description = "Resource Management Endpoints")
public class ResourcesController {
    private final ResourceCommandService resourceCommandService;
    private final ResourceQueryService resourceQueryService;

    public ResourcesController(ResourceCommandService resourceCommandService, ResourceQueryService resourceQueryService) {
        this.resourceCommandService = resourceCommandService;
        this.resourceQueryService = resourceQueryService;
    }

    /**
     * This method creates a new resource.
     * @param res
     * @return ResponseEntity<ResourceResource>
     */
    @PostMapping
    public ResponseEntity<ResourceResource> createResource(@RequestBody CreateResourceResource res) {
        var createResourceCommand = CreateResourceCommandFromResourceAssembler.toCommandFromResource(res);
        var resourceId = resourceCommandService.handle(createResourceCommand);
        if (resourceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(resource.get());
        return new ResponseEntity<>(resourceResource, HttpStatus.CREATED);
    }

    /**
     * This method gets all resources.
     * @return ResponseEntity<List<ResourceResource>>
     */
    @GetMapping
    public ResponseEntity<List<ResourceResource>> getAllResources() {
        var getAllResourcesQuery = new GetAllResourcesQuery();
        var resources = resourceQueryService.handle(getAllResourcesQuery);
        var resourceResources = resources.stream().map(ResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resourceResources);
    }

    /**
     * This method gets a resource by its ID.
     * @param resourceId
     * @return ResponseEntity<ResourceResource>
     */
    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> getResourceById(@PathVariable Long resourceId) {
        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(resource.get());
        return ResponseEntity.ok(resourceResource);
    }

    /**
     * This method updates a resource.
     * @param resourceId
     * @param res
     * @return ResponseEntity<ResourceResource>
     */
    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> updateResource(@PathVariable Long resourceId, @RequestBody UpdateResourceResource res) {
        var updateResourceCommand = UpdateResourceCommandFromResourceAssembler.toCommandFromResource(resourceId, res);
        var updateResource = resourceCommandService.handle(updateResourceCommand);
        if (updateResource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(updateResource.get());
        return ResponseEntity.ok(resourceResource);
    }

    /**
     * This method deletes a resource.
     * @param resourceId
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable Long resourceId) {
        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deleteResourceCommand = new DeleteResourceCommand(resourceId);
        resourceCommandService.handle(deleteResourceCommand);
        return ResponseEntity.ok("Resource with given id successfully deleted");
    }
}
