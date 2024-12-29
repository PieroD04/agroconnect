package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.user.domain.model.commands.DeleteAvailableDateCommand;
import com.acme.web.services.user.domain.model.queries.GetAllAvailableDatesQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDateByIdQuery;
import com.acme.web.services.user.domain.services.AvailableDateCommandService;
import com.acme.web.services.user.domain.services.AvailableDateQueryService;
import com.acme.web.services.user.interfaces.rest.resources.AvailableDateResource;
import com.acme.web.services.user.interfaces.rest.resources.CreateAvailableDateResource;
import com.acme.web.services.user.interfaces.rest.resources.UpdateAvailableDateResource;
import com.acme.web.services.user.interfaces.rest.transform.AvailableDateResourceFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.CreateAvailableDateCommandFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.UpdateAvailableDateCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This class represents the REST controller for the Available Dates.
 * It contains the endpoints for the Available Dates.
 * @author Sebastián Roberto Paredes Puente - U202217239
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api/v1/available-dates", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Available Dates", description = "Available Date Management Endpoints")
public class AvailableDatesController {
    private final AvailableDateCommandService availableDateCommandService;
    private final AvailableDateQueryService availableDateQueryService;

    public AvailableDatesController(AvailableDateCommandService availableDateCommandService, AvailableDateQueryService availableDateQueryService) {
        this.availableDateCommandService = availableDateCommandService;
        this.availableDateQueryService = availableDateQueryService;
    }

    @PostMapping
    public ResponseEntity<AvailableDateResource> createAvailableDate(@RequestBody CreateAvailableDateResource resource){
        var createAvailableDateCommand = CreateAvailableDateCommandFromEntityAssembler.toCommandFromResource(resource);
        var availableDateId = availableDateCommandService.handle(createAvailableDateCommand);
        if (availableDateId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAvailableDateByIdQuery = new GetAvailableDateByIdQuery(availableDateId);
        var availableDate = availableDateQueryService.handle(getAvailableDateByIdQuery);
        if (availableDate.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var availableDateResource = AvailableDateResourceFromEntityAssembler.toResourceFromEntity(availableDate.get());
        return new ResponseEntity<>(availableDateResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvailableDateResource>> getAllAvailableDates() {
        var getAllAvailableDatesQuery = new GetAllAvailableDatesQuery();
        var availableDates = availableDateQueryService.handle(getAllAvailableDatesQuery);
        var availableDateResources = availableDates.stream().map(AvailableDateResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(availableDateResources);
    }

    @GetMapping("/{availableDateId}")
    public ResponseEntity<AvailableDateResource> getAvailableDateById(@PathVariable Long availableDateId) {
        var getAvailableDateByIdQuery = new GetAvailableDateByIdQuery(availableDateId);
        var availableDate = availableDateQueryService.handle(getAvailableDateByIdQuery);
        if (availableDate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var availableDateResource = AvailableDateResourceFromEntityAssembler.toResourceFromEntity(availableDate.get());
        return ResponseEntity.ok(availableDateResource);
    }

    @PutMapping ("/{availableDateId}")
    public ResponseEntity<AvailableDateResource> updateAvailableDate(@RequestBody UpdateAvailableDateResource resource, @PathVariable Long availableDateId) {
        var updateAvailableDateCommand = UpdateAvailableDateCommandFromResourceAssembler.toCommandFromResource(resource, availableDateId);
        var updatedAvailableDate = availableDateCommandService.handle(updateAvailableDateCommand);
        if (updatedAvailableDate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updatedAvailableDateResource = AvailableDateResourceFromEntityAssembler.toResourceFromEntity(updatedAvailableDate.get());
        return ResponseEntity.ok(updatedAvailableDateResource);
    }

    @DeleteMapping("/{availableDateId}")
    public ResponseEntity<?> deleteAvailableDate(@PathVariable Long availableDateId) {
        var getAvailableDateByIdQuery = new GetAvailableDateByIdQuery(availableDateId);
        var availableDate = availableDateQueryService.handle(getAvailableDateByIdQuery);
        if (availableDate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deleteAvailableDateCommand = new DeleteAvailableDateCommand(availableDateId);
        availableDateCommandService.handle(deleteAvailableDateCommand);
        return ResponseEntity.ok("Available Date deleted successfully!");
    }
}
