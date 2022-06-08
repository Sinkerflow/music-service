package com.sinkerflow.api;

import com.sinkerflow.api.mapper.ArtistMapper;
import com.sinkerflow.api.model.ArtistIn;
import com.sinkerflow.api.model.ArtistOut;
import com.sinkerflow.dao.entity.ArtistEntity;
import com.sinkerflow.service.ArtistService;
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ArtistIn artistIn) {
        ArtistEntity entity = mapper.inToEntity(artistIn);
        ArtistEntity result = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.entityToOut(result));
    }

    @GetMapping
    public ResponseEntity<?> retrieveAll() {
        return ResponseEntity.ok(mapper.entityToOutList(service.findAll()));
    }

    @GetMapping("/{artistId}")
    public ArtistOut retrieveById(@PathVariable String artistId) {
        return mapper.entityToOut(service.findById(UUID.fromString(artistId)));
    }

    @PutMapping
    public ArtistOut update(@RequestBody @Valid ArtistIn artistIn) {
        return Optional.of(service.update(mapper.inToEntity(artistIn)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
