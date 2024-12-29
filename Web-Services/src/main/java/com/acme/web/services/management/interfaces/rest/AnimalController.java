package com.acme.web.services.management.interfaces.rest;


import com.acme.web.services.management.domain.model.commands.DeleteAnimalCommand;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsByCageIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsQuery;
import com.acme.web.services.management.domain.model.queries.GetAnimalByIdQuery;
import com.acme.web.services.management.domain.services.AnimalCommandService;
import com.acme.web.services.management.domain.services.AnimalQueryService;
import com.acme.web.services.management.interfaces.rest.resources.AnimalResource;
import com.acme.web.services.management.interfaces.rest.resources.CreateAnimalResource;
import com.acme.web.services.management.interfaces.rest.resources.UpdateAnimalResource;
import com.acme.web.services.management.interfaces.rest.transform.AnimalResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.transform.CreateAnimalCommandFromResourceAssembler;
import com.acme.web.services.management.interfaces.rest.transform.UpdateAnimalCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Animal Controller
 * This class represents the REST controller for the Animal entity.
 * It contains the endpoints for the Animal entity.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/animals", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Animals", description = "Animal Management Endpoints")
public class AnimalController {
    private final AnimalCommandService animalCommandService;
    private final AnimalQueryService animalQueryService;


    public AnimalController(AnimalCommandService animalCommandService, AnimalQueryService animalQueryService) {
        this.animalCommandService = animalCommandService;
        this.animalQueryService = animalQueryService;
    }

    /**
     * This method creates an animal.
     * @param res
     * @return ResponseEntity<AnimalResource>
     */
    @PostMapping
    public ResponseEntity<AnimalResource> createAnimal(@RequestBody CreateAnimalResource res) {
        var createAnimalCommand = CreateAnimalCommandFromResourceAssembler.toCommandFromResource(res);
        var animalId = animalCommandService.handle(createAnimalCommand);
        if (animalId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAnimalByIdQuery = new GetAnimalByIdQuery(animalId);
        var animal = animalQueryService.handle(getAnimalByIdQuery);
        if (animal.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var animalResource = AnimalResourceFromEntityAssembler.toResourceFromEntity(animal.get());
        return new ResponseEntity<>(animalResource, HttpStatus.CREATED);
    }

    /**
     * This method gets all animals by cage ID.
     * @return ResponseEntity<List<AnimalResource>>
     */
    @GetMapping
    public ResponseEntity<List<AnimalResource>> getAllAnimals() {
        var getAllAnimalsQuery = new GetAllAnimalsQuery();
        var animals = animalQueryService.handle(getAllAnimalsQuery);
        var animalResources = animals.stream().map(AnimalResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(animalResources);
    }

    /**
     * This method gets all animals by cage ID.
     * @param animalId
     * @return ResponseEntity<List<AnimalResource>>
     */
    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalResource> getAnimalById(@PathVariable Long animalId) {
        var getAnimalByIdQuery = new GetAnimalByIdQuery(animalId);
        var animal = animalQueryService.handle(getAnimalByIdQuery);
        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var animalResource = AnimalResourceFromEntityAssembler.toResourceFromEntity(animal.get());
        return ResponseEntity.ok(animalResource);
    }


    /**
     * This method updates an animal by ID.
     * @param animalId
     * @param res
     */
    @PutMapping("/{animalId}")
    public ResponseEntity<AnimalResource> updateAnimal(@PathVariable Long animalId, @RequestBody UpdateAnimalResource res) {
        var updateAnimalCommand = UpdateAnimalCommandFromResourceAssembler.toCommandFromResource(animalId, res);
        var updatedAnimal = animalCommandService.handle(updateAnimalCommand);
        if (updatedAnimal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var animalResource = AnimalResourceFromEntityAssembler.toResourceFromEntity(updatedAnimal.get());
        return ResponseEntity.ok(animalResource);
    }

    /**
     * This method deletes an animal by ID.
     * @param animalId
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{animalId}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long animalId) {
        var getAnimalByIdQuery = new GetAnimalByIdQuery(animalId);
        var animal = animalQueryService.handle(getAnimalByIdQuery);
        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deleteAnimalCommand = new DeleteAnimalCommand(animalId);
        animalCommandService.handle(deleteAnimalCommand);
        return ResponseEntity.ok("Animal deleted successfully!");
    }
}
