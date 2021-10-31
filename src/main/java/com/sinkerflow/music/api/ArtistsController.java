package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.ArtistDto;
import com.sinkerflow.music.api.mapper.AuthorMapper;
import com.sinkerflow.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class ArtistsController {

    private final AuthorMapper mapper;
    private final ArtistService artistService;

    @Autowired
    public ArtistsController(AuthorMapper mapper, ArtistService artistService) {
        this.mapper = mapper;
        this.artistService = artistService;
    }

    @GetMapping
    public List<ArtistDto> retrieveAuthors() {
        return artistService.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public ArtistDto retrieveAuthorById(@PathVariable("id") UUID id) {
        return mapper.entityToDto(artistService.findOne(id));
    }

    @GetMapping("/name/{name}")
    public ArtistDto retrieveAuthorByName(@PathVariable("name") String name) {
        return mapper.entityToDto(artistService.findOne(name));
    }

    @PostMapping
    public ArtistDto saveOrUpdateAuthor(@RequestBody ArtistDto author) {
        return mapper.entityToDto(artistService.saveOrUpdate(mapper.dtoToEntity(author)));
    }

    @DeleteMapping("/artists/{id}")
    public void deleteById(@PathVariable UUID id) {
        artistService.delete(id);
    }
}
