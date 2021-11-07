package com.sinkerflow.music.api;

import com.sinkerflow.music.api.dto.MusicIn;
import com.sinkerflow.music.api.mapper.MusicMapper;
import com.sinkerflow.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class MusicController {

    private final MusicMapper mapper;
    private final MusicService musicService;

    @Autowired
    public MusicController(MusicMapper mapper, MusicService musicService) {
        this.mapper = mapper;
        this.musicService = musicService;
    }

    @GetMapping("/music")
    List<MusicIn> retrieveMusicList() {
        return musicService.findAll().stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/music")
    MusicIn saveMusic(MusicIn music) {
        return mapper.entityToDto(musicService.saveOrUpdate(mapper.dtoToEntity(music)));
    }

    @PostMapping("/music/{id}")
    void deleteMusicById(@PathVariable("id") UUID id) {
        musicService.delete(id);
    }
}
