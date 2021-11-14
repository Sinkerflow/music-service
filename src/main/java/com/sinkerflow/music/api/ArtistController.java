package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.ArtistIn;
import com.sinkerflow.music.api.dto.ArtistOut;
import com.sinkerflow.music.api.mapper.ArtistMapper;
import com.sinkerflow.music.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistMapper mapper;
    private final ArtistService service;

    // CREATE
    @PostMapping
    public ArtistOut create(@RequestBody @Valid ArtistIn dto) {
        return Optional.of(service.create(mapper.inToEntity(dto)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // READ
    @GetMapping
    public List<ArtistOut> retrieveAll() {
        return service.find().stream()
                .map(mapper::entityToOut)
                .collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping
    public ArtistOut update(@RequestBody @Valid ArtistIn dto) {
        return Optional.of(service.update(mapper.inToEntity(dto)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
