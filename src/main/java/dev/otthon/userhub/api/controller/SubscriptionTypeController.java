package dev.otthon.userhub.api.controller;

import dev.otthon.userhub.api.routes.ApiRoutes;
import dev.otthon.userhub.application.service.SubscriptionTypeService;
import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.application.service.SubscriptionTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class SubscriptionTypeController {

    private final SubscriptionTypeService service;

    @PostMapping(ApiRoutes.SUBSCRIPTION_TYPE)
    public ResponseEntity<SubscriptionTypeDTO> create(@RequestBody final CreateSubscriptionTypeRequest request) {

        SubscriptionTypeDTO created = service.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);

    }
}
