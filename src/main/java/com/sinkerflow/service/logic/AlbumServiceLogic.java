package com.sinkerflow.service.logic;

import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.api.handler.exception.AlbumNotFoundException;
import com.sinkerflow.api.handler.exception.SinkerException;
import com.sinkerflow.api.model.type.AlbumType;
import com.sinkerflow.dao.entity.AlbumEntity;
import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.dao.repository.AlbumRepository;
import com.sinkerflow.dao.repository.ArtistRepository;
import com.sinkerflow.service.AlbumService;
import com.sinkerflow.service.ArtistService;
import com.sinkerflow.service.AuditService;
import com.sinkerflow.service.validation.AlbumValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sinkerflow.api.handler.BusinessCode.ARTIST_1000;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumServiceLogic implements AlbumService {

    private final AlbumRepository albumRepository;

    private final AuditService auditService;

    private final ArtistService artistService;

    private final ArtistRepository artistRepository;

    private final AlbumValidationService validation;

    @Transactional
    @Override
    public AlbumEntity create(AlbumEntity entity) {
        entity.setId(UUID.randomUUID());

        enrichAlbumWithArtists(entity);

        enrichAlbumFields(entity);

        entity.setAudit(new AuditEntity());
        return albumRepository.save(entity);
    }

    private void enrichAlbumWithArtists(AlbumEntity entity) {
        if (entity.getArtists() != null) {
            entity.setArtists(entity.getArtists().stream()
                    .map(artist -> {
                        if (artist.getId() != null) {
                            if (!artistRepository.existsById(artist.getId())) {
                                throw new SinkerException(Entry.of(ARTIST_1000, String.format("Artist id '%s'", artist.getId())));
                            }
                            return artistService.update(artist);
                        } else {
                            return artistService.create(artist);
                        }
                    })
                    .collect(Collectors.toSet()));
        } else {
            entity.setArtists(Collections.emptySet());
        }
    }

    private void enrichAlbumFields(AlbumEntity entity) {
        if (Objects.isNull(entity.getType())) {
            entity.setType(AlbumType.UNKNOWN);
        }

        if (Objects.isNull(entity.getUrl()) || entity.getUrl().length() == 0) {
            entity.setUrl(UUID.randomUUID().toString().replaceAll("[-]*", ""));
        }

        if (Objects.isNull(entity.getReleased())) {
            entity.setReleased(null);
        }
    }

    @Override
    public List<AlbumEntity> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public List<AlbumEntity> findAllByName(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public AlbumEntity findById(UUID id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(Entry.of(BusinessCode.ALBUM_1000)));
    }

    @Override
    public AlbumEntity update(AlbumEntity entity) {
        var storedEntityOptional = albumRepository.findById(entity.getId());
        if (storedEntityOptional.isPresent()) {
            var storedEntity = storedEntityOptional.get();

            updateStoredEntity(entity, storedEntity);

            return albumRepository.save(storedEntity);
        } else {
            throw new AlbumNotFoundException(Entry.of(BusinessCode.ALBUM_1000));
        }
    }

    private void updateStoredEntity(AlbumEntity entity, AlbumEntity storedEntity) {
        storedEntity.setName(entity.getName());
        storedEntity.setUrl(entity.getUrl());
        storedEntity.setDescription(entity.getDescription());
        storedEntity.setCoverId(entity.getCoverId());
        storedEntity.setArtists(entity.getArtists());
        storedEntity.setReleased(entity.getReleased());
        storedEntity.setAudit(auditService.update(storedEntity.getAudit()));
    }

    @Override
    public void delete(UUID albumId) {
        validation.onDelete(albumId);

        if (!albumRepository.existsById(albumId))
            throw new AlbumNotFoundException(Entry.of(BusinessCode.ARTIST_1001));
        albumRepository.deleteById(albumId);
    }
}
