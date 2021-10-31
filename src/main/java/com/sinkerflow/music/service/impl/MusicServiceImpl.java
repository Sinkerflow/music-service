package com.sinkerflow.music.service.impl;

import com.sinkerflow.music.dao.model.Music;
import com.sinkerflow.music.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MusicServiceImpl implements MusicService {

    @Override
    public List<Music> findAll() {
        return null;
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
