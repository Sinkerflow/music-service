package com.sinkerflow.music.api;

import com.sinkerflow.music.api.model.MusicIn;
import com.sinkerflow.music.api.model.MusicOut;
import com.sinkerflow.music.api.mapper.MusicMapper;
import com.sinkerflow.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicMapper mapper;
    private final MusicService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MusicIn musicIn) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToOut(service.create(mapper.inToEntity(musicIn))));
    }

    // READ
    @GetMapping
    public ResponseEntity<?> retrieveAll() {
        return ResponseEntity.ok(mapper.entityToOutList(service.find()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable String id) {
        return ResponseEntity.ok(mapper.entityToOut(service.findOne(UUID.fromString(id))));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> retrieveByName(@PathVariable String name) {
        return ResponseEntity.ok(mapper.entityToOutList(service.find(name)));
    }

    // UPDATE
    @PutMapping
    public MusicOut update(@RequestBody @Valid MusicIn musicIn) {
        return Optional.of(service.update(mapper.inToEntity(musicIn)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

