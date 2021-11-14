package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.MusicIn;
import com.sinkerflow.music.api.dto.MusicOut;
import com.sinkerflow.music.api.mapper.MusicMapper;
import com.sinkerflow.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicMapper mapper;
    private final MusicService service;

    // CREATE
    @PostMapping
    public MusicOut create(@RequestBody @Valid MusicIn dto) {
        return Optional.of(service.create(mapper.inToEntity(dto)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    // READ
    @GetMapping
    public List<MusicOut> retrieveAll() {
        return service.find().stream()
                .map(mapper::entityToOut)
                .collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping
    public MusicOut update(@RequestBody @Valid MusicIn dto) {
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
