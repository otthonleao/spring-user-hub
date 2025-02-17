package dev.otthon.userhub.api.controller;

import dev.otthon.userhub.api.routes.ApiRoutes;
import dev.otthon.userhub.application.service.SubscriptionTypeService;
import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.dto.request.UpdateSubscriptionTypeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionTypeController {

    private final SubscriptionTypeService service;

    @PostMapping(ApiRoutes.SUBSCRIPTION_TYPE)
    public ResponseEntity<SubscriptionTypeDTO> create(@Valid @RequestBody final CreateSubscriptionTypeRequest request) {

        SubscriptionTypeDTO created = service.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);

    }

    @GetMapping(ApiRoutes.SUBSCRIPTION_TYPE)
    public ResponseEntity<List<SubscriptionTypeDTO>> findAll() {
        List<SubscriptionTypeDTO> response = service.listAll();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(response);
    }

    @GetMapping(ApiRoutes.SUBSCRIPTION_TYPE + "/{id}")
    public ResponseEntity<SubscriptionTypeDTO> findById(@PathVariable Long id) {
        SubscriptionTypeDTO response = service.getById(id);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(response);
    }

    @DeleteMapping(ApiRoutes.SUBSCRIPTION_TYPE + "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(ApiRoutes.SUBSCRIPTION_TYPE + "/{id}")
    public ResponseEntity<SubscriptionTypeDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateSubscriptionTypeRequest request) {
        SubscriptionTypeDTO updated = service.update(id, request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(updated);
    }

    @PatchMapping(ApiRoutes.SUBSCRIPTION_TYPE + "/{id}")
    public ResponseEntity<SubscriptionTypeDTO> parcialUpdate(@PathVariable Long id, @Valid @RequestBody UpdateSubscriptionTypeRequest request) {
        SubscriptionTypeDTO updated = service.parcialUpdatePatch(id, request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(updated);
    }
}
