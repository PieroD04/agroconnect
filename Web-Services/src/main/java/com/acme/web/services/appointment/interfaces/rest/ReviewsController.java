package com.acme.web.services.appointment.interfaces.rest;

import com.acme.web.services.appointment.domain.model.queries.GetAllReviewsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetReviewByIdQuery;
import com.acme.web.services.appointment.domain.services.ReviewCommandService;
import com.acme.web.services.appointment.domain.services.ReviewQueryService;
import com.acme.web.services.appointment.interfaces.rest.resources.CreateReviewResource;
import com.acme.web.services.appointment.interfaces.rest.resources.ReviewResource;
import com.acme.web.services.appointment.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import com.acme.web.services.appointment.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import com.acme.web.services.user.application.internal.commandservices.AdvisorCommandServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This class represents the Reviews Controller.
 * It contains the endpoints for the review management.
 * It contains the methods to create a review, get all reviews and get a review by id.
 * @author Andre Gabriel Valverde Mozo
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api/v1/reviews", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewsController {
    private final ReviewCommandService reviewsCommandService;
    private final ReviewQueryService reviewsQueryService;
    private final AdvisorCommandServiceImpl advisorCommandService;

    public ReviewsController(ReviewCommandService reviewsCommandService, ReviewQueryService reviewsQueryService, AdvisorCommandServiceImpl advisorCommandService) {
        this.reviewsCommandService = reviewsCommandService;
        this.reviewsQueryService = reviewsQueryService;
        this.advisorCommandService = advisorCommandService;
    }

    /**
     * Creates a review
     * @param resource the review resource
     * @return the created review
     */
    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource){
        var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
        var reviewId = reviewsCommandService.handle(createReviewCommand);
        if (reviewId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        advisorCommandService.updateAdvisorRating(reviewId);
        var getReviewByIdQuery = new GetReviewByIdQuery(reviewId);
        var review = reviewsQueryService.handle(getReviewByIdQuery);
        if (review.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

    /**
     * Gets all reviews
     * @return all reviews
     */
    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        var getAllReviewsQuery = new GetAllReviewsQuery();
        var reviews = reviewsQueryService.handle(getAllReviewsQuery);
        var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reviewResources);
    }

    /**
     * Gets a review by id
     * @param reviewId the review id
     * @return the review
     */
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long reviewId) {
        var getReviewByIdQuery = new GetReviewByIdQuery(reviewId);
        var review = reviewsQueryService.handle(getReviewByIdQuery);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

}
