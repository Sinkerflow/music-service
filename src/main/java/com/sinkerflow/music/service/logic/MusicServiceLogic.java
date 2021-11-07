package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.dao.model.Music;
import com.sinkerflow.music.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MusicServiceLogic implements MusicService {

    @Override
    public List<Music> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Music findOne(UUID musicId) {
        return null;
    }

    @Override
    public Music saveOrUpdate(Music music) {
        return null;
    }

    @Override
    public void delete(UUID musicId) {

    }
}
