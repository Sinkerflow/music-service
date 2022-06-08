package com.sinkerflow.dao.repository;

import com.sinkerflow.dao.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MusicRepository extends JpaRepository<TrackEntity, UUID> {

    boolean existsByName(String artistName);

    Optional<TrackEntity> findByName(String name);

    List<TrackEntity> findByNameLikeOrderByNameAsc(String name);
}
