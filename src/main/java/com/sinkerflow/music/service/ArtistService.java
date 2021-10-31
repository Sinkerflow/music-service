package com.sinkerflow.music.service;

import com.sinkerflow.music.dao.model.Artist;

import java.util.List;
import java.util.UUID;

public interface ArtistService {

    List<Artist> findAll();

    Artist findOne(UUID artistId);

    Artist findOne(String artistName);

    Artist saveOrUpdate(Artist artist);

    void delete(UUID authorId);
}
