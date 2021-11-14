package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.AlbumIn;
import com.sinkerflow.music.api.dto.AlbumOut;
import com.sinkerflow.music.api.mapper.AlbumMapper;
import com.sinkerflow.music.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumMapper mapper;
    private final AlbumService service;

    // CREATE
    @PostMapping
    public AlbumOut create(@RequestBody @Valid AlbumIn dto) {
        return Optional.of(service.create(mapper.inToEntity(dto)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // READ
    @GetMapping
    public List<AlbumOut> retrieveAll() {
        return service.find().stream()
                .map(mapper::entityToOut)
                .collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping
    public AlbumOut update(@RequestBody @Valid AlbumIn dto) {
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
