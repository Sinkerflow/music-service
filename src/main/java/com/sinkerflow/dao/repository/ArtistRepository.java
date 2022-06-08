package com.sinkerflow.dao.repository;

import com.sinkerflow.dao.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, UUID> {

    boolean existsByName(String artistName);

    boolean existsByUrl(String artistUrl);

    Optional<ArtistEntity> findByName(String artistName);

    List<ArtistEntity> findByNameLikeOrderByNameAsc(String artistName);
}
