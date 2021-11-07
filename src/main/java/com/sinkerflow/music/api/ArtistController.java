package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.ArtistIn;
import com.sinkerflow.music.api.dto.ArtistOut;
import com.sinkerflow.music.api.mapper.ArtistMapper;
import com.sinkerflow.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistMapper mapper;
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistMapper mapper, ArtistService artistService) {
        this.mapper = mapper;
        this.artistService = artistService;
    }

    @GetMapping
    public List<ArtistOut> retrieveAuthors() {
        return artistService.find().stream()
                .map(mapper::entityToOut)
                .collect(Collectors.toList());
    }

    // CREATE
    @PostMapping
    public ArtistOut createArtist(@RequestBody @Valid ArtistIn artist) {
        return Optional.of(artistService.create(mapper.inToEntity(artist)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // READ
    @GetMapping("/id/{id}")
    public ArtistOut retrieveAuthorById(@PathVariable("id") UUID id) {
        return Optional.of(artistService.findOne(id))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    @GetMapping("/name/{name}")
    public ArtistOut retrieveAuthorByName(@PathVariable("name") String name) {
        return Optional.of(artistService.findOne(name))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // UPDATE
    @PutMapping
    public ArtistOut updateArtist(@RequestBody @Valid ArtistIn artist) {
        return Optional.of(artistService.update(mapper.inToEntity(artist)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        artistService.delete(id);
    }
}
