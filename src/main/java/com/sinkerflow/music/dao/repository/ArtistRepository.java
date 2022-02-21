package com.sinkerflow.music.dao.repository;

import com.sinkerflow.music.dao.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, UUID> {

    boolean existsByName(String artistName);

    boolean existsByUrl(String artistUrl);

    Optional<Artist> findByName(String artistName);

    Collection<Artist> findByNameLikeOrderByNameAsc(String artistName);
}
