package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.dao.model.Artist;
import com.sinkerflow.music.dao.model.Audit;
import com.sinkerflow.music.dao.repository.ArtistRepository;
import com.sinkerflow.music.service.ArtistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ArtistServiceLogicTest {

    @Autowired
    private ArtistService artistService;

    @MockBean
    private ArtistRepository repository;

    @Test
    void create() {
        final String name = "Sting";
        final String description = "Some description";
        final String avatarUrl = "/files/32897492.png";
        final String url = "sting";
        final Set<UUID> albumIds = Set.of(UUID.randomUUID(), UUID.randomUUID());
        final Instant createdAt = Instant.parse("2021-12-03T10:15:30.00Z");
        final Instant updatedAt = Instant.parse("2021-12-09T10:15:30.00Z");

        Artist entity = new Artist();
        entity.setName(name);
        entity.setDescription(description);
        entity.setAvatarUrl(avatarUrl);
        entity.setUrl(url);
        entity.setAlbumIds(albumIds);
        Audit audit = new Audit();
        audit.setCreatedAt(createdAt);
        audit.setUpdatedAt(updatedAt);

        doReturn(false).when(repository)
                .existsByName(entity.getName());
        doReturn(entity).when(repository)
                .save(entity);

        final Artist createdEntity = artistService.create(entity);

        Assertions.assertEquals(name, createdEntity.getName());
    }

    @Test
    void findAll_whenArtistsExist_thenSuccess() {
        final String name = "Sting";
        final String description = "Some description";
        final String avatarUrl = "/files/32897492.png";
        final String url = "sting";
        final Set<UUID> albumIds = Set.of(UUID.randomUUID(), UUID.randomUUID());
        final Instant createdAt = Instant.parse("2021-12-03T10:15:30.00Z");
        final Instant updatedAt = Instant.parse("2021-12-09T10:15:30.00Z");

        Artist entity = new Artist();
        entity.setName(name);
        entity.setDescription(description);
        entity.setAvatarUrl(avatarUrl);
        entity.setUrl(url);
        entity.setAlbumIds(albumIds);
        Audit audit = new Audit();
        audit.setCreatedAt(createdAt);
        audit.setUpdatedAt(updatedAt);

        doReturn(List.of(entity)).when(repository)
                .findAll();

        final Collection<Artist> artists = artistService.find();

        Assertions.assertEquals(List.of(entity), artists);
    }

    @Test
    void findOneByName_whenNameExists_thenSuccess() {
        final String name = "Sting";
        final String description = "Some description";
        final String avatarUrl = "/files/32897492.png";
        final String url = "sting";
        final Set<UUID> albumIds = Set.of(UUID.randomUUID(), UUID.randomUUID());
        final Instant createdAt = Instant.parse("2021-12-03T10:15:30.00Z");
        final Instant updatedAt = Instant.parse("2021-12-09T10:15:30.00Z");

        Artist entity = new Artist();
        entity.setName(name);
        entity.setDescription(description);
        entity.setAvatarUrl(avatarUrl);
        entity.setUrl(url);
        entity.setAlbumIds(albumIds);
        Audit audit = new Audit();
        audit.setCreatedAt(createdAt);
        audit.setUpdatedAt(updatedAt);

        doReturn(Optional.of(entity)).when(repository)
                .findByName(name);

        final Artist artist = artistService.findOne(name);

        Assertions.assertEquals(entity, artist);
    }
}
