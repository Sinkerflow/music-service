package com.sinkerflow.service.logic;

import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.api.handler.exception.ArtistNotFoundException;
import com.sinkerflow.api.handler.exception.ResourceAlreadyExistsException;
import com.sinkerflow.dao.entity.ArtistEntity;
import com.sinkerflow.dao.entity.AuditEntity;
import com.sinkerflow.dao.entity.TrackEntity;
import com.sinkerflow.dao.repository.AlbumRepository;
import com.sinkerflow.dao.repository.ArtistRepository;
import com.sinkerflow.dao.repository.MusicRepository;
import com.sinkerflow.service.ArtistService;
import com.sinkerflow.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistServiceLogic implements ArtistService {

    private final ArtistRepository repository;

    private final AuditService auditService;

    private final AlbumRepository albumRepository;

    private final MusicRepository musicRepository;

    @Override
    public ArtistEntity create(ArtistEntity entity) {
        if (repository.existsByUrl(entity.getUrl())) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.ARTIST_1003));
        }

        if (repository.existsByName(entity.getName())) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.ARTIST_1002));
        }
        // enrichAlbums(entity);
        enrichMusics(entity);
        entity.setId(UUID.randomUUID());
        entity.setAudit(new AuditEntity());

        return repository.save(entity);
    }

    @Override
    public List<ArtistEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ArtistEntity> findAllByName(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public ArtistEntity findById(UUID artistId) {
        return repository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1000)));
    }

    @Override
    public ArtistEntity update(ArtistEntity entity) {
        var storedEntityOptional = repository.findById(entity.getId());

        if (storedEntityOptional.isEmpty()) {
            throw new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1000));
        }
        var storedEntity = storedEntityOptional.get();
        storedEntity.setName(entity.getName());
        storedEntity.setDescription(entity.getDescription());
        storedEntity.setUrl(entity.getUrl());
        storedEntity.setAvatarId(entity.getAvatarId());

        storedEntity.setAudit(auditService.update(storedEntity.getAudit()));

        return repository.save(storedEntity);
    }

    @Override
    public void delete(UUID artistId) {
        if (!repository.existsById(artistId))
            throw new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1001));
        repository.deleteById(artistId);
    }

    private void enrichMusics(ArtistEntity entity) {
        if (entity.getTracks() != null && !entity.getTracks().isEmpty()) {
            List<UUID> musicIds = entity.getTracks().stream()
                    .map(TrackEntity::getId)
                    .collect(Collectors.toList());

            Set<TrackEntity> musics = new HashSet<>(musicRepository.findAllById(musicIds));

            if (musics.isEmpty()) {
                throw new RuntimeException("Albums are not found in database"); // todo: get a new error code
            }

            entity.setTracks(musics);
        }
    }

//    private void enrichAlbums(ArtistEntity entity) {
//        if (!entity.getAlbums().isEmpty()) {
//            List<UUID> albumsIds = entity.getAlbums().stream()
//                    .map(AlbumEntity::getId)
//                    .collect(Collectors.toList());
//
//            List<AlbumEntity> albums = albumRepository.findAllById(albumsIds).stream()
//                    .peek(artist -> artist.getArtists().clear())
//                    .collect(Collectors.toList());
//
//            if (albums.isEmpty()) {
//                throw new RuntimeException("Albums are not found in database"); // todo: get a new error code
//            }
//
//            entity.setAlbums(new HashSet<>(albums));
//        }
//    }
}
