package com.sinkerflow.music.dao.repository;

import com.sinkerflow.music.dao.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends MongoRepository<Album, UUID> {

    Optional<Album> findByUrl(String url);

    Collection<Album> findByNameLikeOrderByNameAsc(String albumName);
}
