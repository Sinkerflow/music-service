package com.sinkerflow.music.dao.repository;

import com.sinkerflow.music.dao.model.Artist;
import com.sinkerflow.music.dao.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MusicRepository extends MongoRepository<Music, UUID> {

    boolean existsByName(String artistName);

    Optional<Music> findByName(String name);

    Collection<Music> findByNameLikeOrderByNameAsc(String name);
}
