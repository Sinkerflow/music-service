package com.sinkerflow.api;

import com.sinkerflow.api.mapper.AlbumMapper;
import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.api.model.AlbumOut;
import com.sinkerflow.dao.entity.AlbumEntity;
import com.sinkerflow.service.AlbumService;
import com.sinkerflow.service.validation.AlbumValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AlbumValidationService validationService;

    @PostMapping
    public ResponseEntity<AlbumOut> create(@RequestBody @Valid AlbumIn albumIn) {
        if (albumIn.getUrl() != null) {
            albumIn.setUrl(albumIn.getUrl().replaceAll(" ", "_"));
        }
        validationService.onCreate(albumIn);
        AlbumEntity entity = service.create(mapper.inToEntity(albumIn));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.entityToOut(entity));
    }

    @GetMapping
    public List<AlbumOut> retrieveAll() {
        return service.findAll().stream()
                .map(mapper::entityToOut)
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{name}")
    public List<AlbumOut> findAllByAlbumName(@PathVariable String name) {
        return mapper.entitiesToOut(service.findAllByName(name));
    }

    @PutMapping
    public AlbumOut update(@RequestBody @Valid AlbumIn albumIn) {
        return Optional.of(service.update(mapper.inToEntity(albumIn)))
                .map(mapper::entityToOut)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
