package com.sinkerflow.dao.repository;

import com.sinkerflow.dao.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, UUID> {

    boolean existsByUrl(String name);

    Optional<AlbumEntity> findByUrl(String url);

    List<AlbumEntity> findByNameContainingIgnoreCase(String albumName);
}
