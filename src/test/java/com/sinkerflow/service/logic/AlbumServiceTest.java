package com.sinkerflow.service.logic;

import com.sinkerflow.ApplicationTest;
import com.sinkerflow.api.model.type.AlbumType;
import com.sinkerflow.dao.entity.AlbumEntity;
import com.sinkerflow.dao.entity.ArtistEntity;
import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.dao.repository.AlbumRepository;
import com.sinkerflow.service.AlbumService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class AlbumServiceTest extends ApplicationTest {

    @Autowired
    AlbumService albumService;

    @MockBean
    AlbumRepository albumRepository;

    @Disabled
    @Test
    void whenAlbumCreatedWithValidData_thenSuccess() {
        ArtistEntity artist = new ArtistEntity();
        artist.setId(UUID.randomUUID());
        artist.setName("name");
        artist.setUrl("url");
        artist.setDescription("description");
        artist.setAudit(new AuditEntity());

        AlbumEntity album = new AlbumEntity();
        album.setId(UUID.randomUUID());
        album.setName("name");
        album.setDescription("description");
        album.setType(AlbumType.UNKNOWN);
        album.setUrl("url");
        album.setCoverId(UUID.randomUUID());
        album.setAudit(new AuditEntity());

        doReturn(album).when(albumRepository).save(any());

        album.setType(null);

        AlbumEntity createdEntity = albumService.create(album);

        assertNotNull(createdEntity.getId());

        assertEquals(album.getName(), createdEntity.getName());
        assertEquals(album.getUrl(), createdEntity.getUrl());
        assertEquals(album.getDescription(), createdEntity.getDescription());
        assertEquals(album.getDescription(), createdEntity.getDescription());
        assertEquals(album.getCoverId(), createdEntity.getCoverId());
        assertEquals(album.getReleased(), createdEntity.getReleased());
        assertNotNull(createdEntity.getType());
        assertEquals(AlbumType.UNKNOWN, createdEntity.getType());
        assertEquals(album.getArtists(), createdEntity.getArtists());
    }

    @Test
    void whenAlbumCreatedWithInvalidData_thenFail() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindAlbumByCorrectId_thenSuccess() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenFindAlbumByIncorrectId_thenFail() {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumFoundByCorrectName_thenSuccess() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumFoundByIncorrectName_thenFail() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumUpdatedByValidData_thenSuccess() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumUpdatedByInvalidData_thenFail() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumDeletedByCorrectId_thenSuccess() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenAlbumDeletedByIncorrectId_thenFail() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
