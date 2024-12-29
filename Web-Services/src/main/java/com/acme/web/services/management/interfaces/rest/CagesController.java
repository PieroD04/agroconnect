package com.acme.web.services.management.interfaces.rest;

import com.acme.web.services.management.domain.model.commands.DeleteCageCommand;
import com.acme.web.services.management.domain.model.queries.GetAllAnimalsByCageIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllCagesByBreederIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllCagesQuery;
import com.acme.web.services.management.domain.model.queries.GetCageByIdQuery;
import com.acme.web.services.management.domain.services.AnimalQueryService;
import com.acme.web.services.management.domain.services.CageCommandService;
import com.acme.web.services.management.domain.services.CageQueryService;
import com.acme.web.services.management.interfaces.rest.resources.AnimalResource;
import com.acme.web.services.management.interfaces.rest.resources.CageResource;
import com.acme.web.services.management.interfaces.rest.resources.CreateCageResource;
import com.acme.web.services.management.interfaces.rest.resources.UpdateCageResource;
import com.acme.web.services.management.interfaces.rest.transform.AnimalResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.transform.CageResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.transform.CreateCageCommandFromResourceAssembler;
import com.acme.web.services.management.interfaces.rest.transform.UpdateCageCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

/**
 * CageController
 * This class represents the REST controller for the Cage entity.
 * It contains the endpoints for the Cage entity.
 * @author Nadia Alessandra Lucas Coronel - u202120430
 * @version 1.0
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/cages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Cages", description = "Cage Management Endpoints")
public class CagesController {
    private final CageCommandService cageCommandService;
    private final CageQueryService cageQueryService;
    private final AnimalQueryService animalQueryService;

    public CagesController(CageCommandService cageCommandService, CageQueryService cageQueryService, AnimalQueryService animalQueryService){
        this.cageCommandService = cageCommandService;
        this.cageQueryService = cageQueryService;
        this.animalQueryService = animalQueryService;
    }

    /**
     * This method creates a new cage.
     * @param res
     * @return ResponseEntity<CageResource>
     */
    @PostMapping
    public ResponseEntity<CageResource> createCage(@RequestBody CreateCageResource res) {
        var createCageCommand = CreateCageCommandFromResourceAssembler.toCommandFromResource(res);
        var cageId = cageCommandService.handle(createCageCommand);
        if (cageId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getCageByIdQuery = new GetCageByIdQuery(cageId);
        var cage = cageQueryService.handle(getCageByIdQuery);
        if (cage.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cageResource = CageResourceFromEntityAssembler.toResourceFromEntity(cage.get());
        return new ResponseEntity<>(cageResource, HttpStatus.CREATED);
    }

    /**
     * This method gets all cages.
     * @return ResponseEntity<List<CageResource>>
     */
    @GetMapping
    public ResponseEntity<List<CageResource>> getAllCages() {
        var getAllCagesQuery = new GetAllCagesQuery();
        var cages = cageQueryService.handle(getAllCagesQuery);
        var cageResources = cages.stream().map(CageResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cageResources);
    }

    /**
     * This method gets a cage by id.
     * @param cageId
     * @return ResponseEntity<CageResource>
     */
    @GetMapping("/{cageId}")
    public ResponseEntity<CageResource> getCageById(@PathVariable Long cageId) {
        var getCageByIdQuery = new GetCageByIdQuery(cageId);
        var cage = cageQueryService.handle(getCageByIdQuery);
        if (cage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cageResource = CageResourceFromEntityAssembler.toResourceFromEntity(cage.get());
        return ResponseEntity.ok(cageResource);
    }

    /**
     * This method gets all animals in a cage.
     * @param cageId
     * @return ResponseEntity<List<AnimalResource>>
     */
    @GetMapping("/{cageId}/animals")
    public ResponseEntity<List<AnimalResource>> getAnimalsByCageId(@PathVariable Long cageId) {
        var getCageByIdQuery = new GetCageByIdQuery(cageId);
        var cage = cageQueryService.handle(getCageByIdQuery);
        if (cage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var getAllAnimalsByCageIdQuery = new GetAllAnimalsByCageIdQuery(cageId);
        var animals = animalQueryService.handle(getAllAnimalsByCageIdQuery);
        var animalResources = animals.stream().map(AnimalResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(animalResources);
    }

    /**
     * This method updates a cage.
     * @param cageId
     * @param res
     * @return ResponseEntity<CageResource>
     */
    @PutMapping("/{cageId}")
    public ResponseEntity<CageResource> updateCage(@PathVariable Long cageId, @RequestBody UpdateCageResource res) {
        var updateCageCommand = UpdateCageCommandFromResourceAssembler.toCommandFromResource(cageId, res);
        var updatedCage = cageCommandService.handle(updateCageCommand);
        if (updatedCage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cageResource = CageResourceFromEntityAssembler.toResourceFromEntity(updatedCage.get());
        return ResponseEntity.ok(cageResource);
    }

    /**
     * This method deletes a cage.
     * @param cageId
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{cageId}")
    public ResponseEntity<?> deleteCage(@PathVariable Long cageId) {
        var getCageByIdQuery = new GetCageByIdQuery(cageId);
        var cage = cageQueryService.handle(getCageByIdQuery);
        if (cage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var deleteCageCommand = new DeleteCageCommand(cageId);
        cageCommandService.handle(deleteCageCommand);
        return ResponseEntity.ok("Cage deleted successfully!");
    }
}
