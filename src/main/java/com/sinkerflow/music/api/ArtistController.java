package com.sinkerflow.music.api;

import com.sinkerflow.music.api.mapper.ArtistMapper;
import com.sinkerflow.music.api.model.ArtistIn;
import com.sinkerflow.music.api.model.ArtistOut;
import com.sinkerflow.music.service.ArtistService;
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
@RequestMapping("/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistMapper mapper;
    private final ArtistService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ArtistIn artistIn) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToOut(service.create(mapper.inToEntity(artistIn))));
    }

    // READ
    @GetMapping
    public ResponseEntity<?> retrieveAll() {
        return ResponseEntity.ok(mapper.entityToOutList(service.find()));
    }

    @GetMapping("/{artistId}")
    public ArtistOut retrieveById(@PathVariable String artistId) {
        return mapper.entityToOut(service.findOne(UUID.fromString(artistId)));
    }

    @GetMapping("/name/{artistName}")
    public ArtistOut retrieveByName(@PathVariable String artistName) {
        return mapper.entityToOut(service.findOne(artistName));
    }

    // UPDATE
    @PutMapping
    public ArtistOut update(@RequestBody @Valid ArtistIn artistIn) {
        return Optional.of(service.update(mapper.inToEntity(artistIn)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
