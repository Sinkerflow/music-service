package com.sinkerflow.service.logic;

import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.api.handler.exception.ResourceNotFoundException;
import com.sinkerflow.dao.entity.ArtistEntity;
import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.dao.entity.TrackEntity;
import com.sinkerflow.dao.repository.ArtistRepository;
import com.sinkerflow.dao.repository.MusicRepository;
import com.sinkerflow.service.AuditService;
import com.sinkerflow.service.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sinkerflow.api.handler.BusinessCode.ALBUM_1000;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceLogic implements TrackService {

    private final MusicRepository repository;

    private final ArtistRepository artistRepository;

    private final AuditService auditService;

    @Override
    public TrackEntity create(TrackEntity entity) {
        entity.setId(UUID.randomUUID());
        enrichArtists(entity);
        entity.setAudit(new AuditEntity());
        return repository.save(entity);
    }

    @Override
    public List<TrackEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TrackEntity> findAllByName(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public TrackEntity findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Entry.of(ALBUM_1000)));
    }

    @Override
    public TrackEntity update(TrackEntity entity) {
        var storedEntityOptional = repository.findById(entity.getId());

        if (storedEntityOptional.isPresent()) {
            var storedEntity = storedEntityOptional.get();

            updateStoredEntity(entity, storedEntity);

            return repository.save(storedEntity);
        } else {
            throw new ResourceNotFoundException(Entry.of(BusinessCode.MUSIC_1000));
        }
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(Entry.of(BusinessCode.MUSIC_1001));
        repository.deleteById(id);
    }

    private void enrichArtists(TrackEntity entity) {
        if (!entity.getArtists().isEmpty()) {

            List<UUID> artistIds = entity.getArtists().stream()
                    .map(ArtistEntity::getId)
                    .collect(Collectors.toList());
            List<ArtistEntity> artists = artistRepository.findAllById(artistIds);

            if (artists.isEmpty()) {
                throw new ResourceNotFoundException(Entry.of(BusinessCode.ARTIST_1000));
            }
        }
    }

    private void updateStoredEntity(TrackEntity entity, TrackEntity storedEntity) {
        storedEntity.setName(entity.getName());
        storedEntity.setUrl(entity.getUrl());
        storedEntity.setDescription(entity.getDescription());
        storedEntity.setArtists(entity.getArtists());
        storedEntity.setAudit(entity.getAudit());
        entity.setAudit(auditService.update(entity.getAudit()));
    }
}
