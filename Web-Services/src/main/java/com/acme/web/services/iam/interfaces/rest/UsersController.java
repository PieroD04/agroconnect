package com.acme.web.services.iam.interfaces.rest;

import com.acme.web.services.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.web.services.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.web.services.iam.domain.services.UserQueryService;
import com.acme.web.services.iam.interfaces.rest.resources.UserResource;
import com.acme.web.services.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.acme.web.services.user.domain.model.queries.GetNotificationsByUserIdQuery;
import com.acme.web.services.user.domain.services.NotificationQueryService;
import com.acme.web.services.user.interfaces.rest.resources.NotificationResource;
import com.acme.web.services.user.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {
    private final UserQueryService userQueryService;
    private final NotificationQueryService notificationQueryService;

    public UsersController(UserQueryService userQueryService, NotificationQueryService notificationQueryService) {
        this.userQueryService = userQueryService;
        this.notificationQueryService = notificationQueryService;
    }

    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserResource
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserResource
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<List<NotificationResource>> getUserNotifications(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var getNotificationsByUserIdQuery = new GetNotificationsByUserIdQuery(userId);
        var notifications = notificationQueryService.handle(getNotificationsByUserIdQuery);
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResources);
    }
}