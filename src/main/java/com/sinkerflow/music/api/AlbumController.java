package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.AlbumOut;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @PostMapping
    public AlbumOut create(AlbumOut album) {
        return null;
    }

    @GetMapping
    public List<AlbumOut> retrieveAll() {
        return null;
    }

    @DeleteMapping
    public void delete(UUID id) {
    }

    @PutMapping
    public AlbumOut update(AlbumOut album) {
        return null;
    }
}
