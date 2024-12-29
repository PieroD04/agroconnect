package com.acme.web.services.appointment.interfaces.rest;


import com.acme.web.services.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.acme.web.services.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewsByAppointmentIdQuery;
import com.acme.web.services.appointment.domain.services.AppointmentCommandService;
import com.acme.web.services.appointment.domain.services.AppointmentQueryService;
import com.acme.web.services.appointment.domain.services.ReviewQueryService;
import com.acme.web.services.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.acme.web.services.appointment.interfaces.rest.resources.AppointmentResource;
import com.acme.web.services.appointment.interfaces.rest.resources.ReviewResource;
import com.acme.web.services.appointment.interfaces.rest.resources.UpdateAppointmentResource;
import com.acme.web.services.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.acme.web.services.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.web.services.appointment.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import com.acme.web.services.appointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This class represents the AppointmentsController.
 * It contains the endpoints for the appointment management.
 * @author Sebastián Roberto Paredes Puente -U202217239
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api/v1/appointments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Appointment Management Endpoints")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    private final ReviewQueryService reviewQueryService;
    private final ApplicationEventPublisher eventPublisher;

    public AppointmentsController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService, ReviewQueryService reviewQueryService, ApplicationEventPublisher eventPublisher) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
        this.reviewQueryService = reviewQueryService;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Creates an appointment
     * @param resource the appointment resource
     * @return the created appointment
     */
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointmentId = appointmentCommandService.handle(createAppointmentCommand);
        if (appointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());

        // Publish the event
        eventPublisher.publishEvent(new CreateNotificationByAppointmentCreated(this, appointmentResource.breederId(), appointmentResource.advisorId()));

        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    /**
     * Gets all appointments
     * @return a list of all appointments
     */
    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointments() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    /**
     * Gets an appointment by id
     * @param appointmentId the appointment id
     * @return the appointment
     */
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    /**
     * Updates an appointment by id
     * @param appointmentId the appointment id
     * @param resource the updated appointment resource
     * @return the updated appointment
     */
    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> updateAppointmentById(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource resource) {
        var updateAppointmentCommand = UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource, appointmentId);
        var updatedAppointmentId = appointmentCommandService.handle(updateAppointmentCommand);
        if (updatedAppointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(updatedAppointmentId);
        var updatedAppointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (updatedAppointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updatedAppointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(updatedAppointment.get());
        return ResponseEntity.ok(updatedAppointmentResource);
    }

    /**
     * Deletes an appointment by id
     * @param appointmentId the appointment id
     * @return the deleted appointment
     */
    @GetMapping("/{appointmentId}/reviews")
    public ResponseEntity<List<ReviewResource>> getAppointmentReviews(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var getReviewsByAppointmentIdQuery = new GetReviewsByAppointmentIdQuery(appointmentId);
        var reviews = reviewQueryService.handle(getReviewsByAppointmentIdQuery);
        var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reviewResources);
    }
}