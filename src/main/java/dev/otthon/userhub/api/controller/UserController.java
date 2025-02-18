package dev.otthon.userhub.api.controller;

import dev.otthon.userhub.api.routes.ApiRoutes;
import dev.otthon.userhub.application.service.UserService;
import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.dto.request.UserUpdateRequest;
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
public class UserController {

    private final UserService service;

    @PostMapping(ApiRoutes.USERS)
    public ResponseEntity<UserDTO> create(@Valid @RequestBody final UserRequest request) {
        UserDTO created = service.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping(ApiRoutes.USERS + "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiRoutes.USERS)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> response = service.listAll();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(response);
    }

    @GetMapping(ApiRoutes.USERS + "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO response = service.getById(id);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.ok().header(HttpHeaders.LOCATION, uri.toString()).body(response);
    }

    @PutMapping(ApiRoutes.USERS + "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody final UserUpdateRequest request) {
        UserDTO updated = service.update(id, request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(uri).body(updated);
    }

}
