package com.sinkerflow.api;

import com.sinkerflow.api.mapper.TrackMapper;
import com.sinkerflow.api.model.TrackIn;
import com.sinkerflow.api.model.TrackOut;
import com.sinkerflow.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackMapper mapper;
    private final TrackService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid TrackIn trackIn) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToOut(service.create(mapper.inToEntity(trackIn))));
    }

    @GetMapping
    public ResponseEntity<?> retrieveAll() {
        return ResponseEntity.ok(mapper.entityToOutList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable String id) {
        return ResponseEntity.ok(mapper.entityToOut(service.findById(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> retrieveByName(@PathVariable String name) {
        return ResponseEntity.ok(mapper.entityToOutList(service.findAllByName(name)));
    }

    @PutMapping
    public TrackOut update(@RequestBody @Valid TrackIn trackIn) {
        return Optional.of(service.update(mapper.inToEntity(trackIn)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
