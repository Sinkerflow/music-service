package com.sinkerflow.music.service;

import com.sinkerflow.music.dao.model.Music;

import java.util.List;
import java.util.UUID;

public interface MusicService {

    List<Music> findAll();

    Music findOne(UUID musicId);

    Music saveOrUpdate(Music music);

    void delete(UUID musicId);
}
