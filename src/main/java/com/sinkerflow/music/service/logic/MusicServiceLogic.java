package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.Entry;
import com.sinkerflow.music.api.handler.exception.ResourceAlreadyExistsException;
import com.sinkerflow.music.api.handler.exception.ResourceNotFoundException;
import com.sinkerflow.music.dao.model.Artist;
import com.sinkerflow.music.dao.model.Music;
import com.sinkerflow.music.dao.repository.MusicRepository;
import com.sinkerflow.music.helper.TokenHelper;
import com.sinkerflow.music.service.ArtistService;
import com.sinkerflow.music.service.AuditService;
import com.sinkerflow.music.service.MusicService;
import com.sinkerflow.music.service.validation.MusicValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static com.sinkerflow.music.api.handler.BusinessCode.ALBUM_1000;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicServiceLogic implements MusicService {

    private final MusicRepository repository;
    private final ArtistService artistService;
    private final AuditService auditService;
    private final MusicValidationService validation;

    @Override
    public Music create(Music entity) {
        var id = entity.getId();

        if (Objects.nonNull(id)) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.MUSIC_1002));
        }

        var artistId = entity.getArtistId();
        Artist artist = null;

        if (Objects.nonNull(artistId)) {
            artist = artistService.findOne(artistId);
        }

        if (Objects.isNull(artist)) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.ARTIST_1000));
        }

        entity.setId(TokenHelper.generate());
        return repository.save(entity);
    }

    @Override
    public Collection<Music> find() {
        return repository.findAll();
    }

    @Override
    public Collection<Music> find(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public Music findOne(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Entry.of(BusinessCode.MUSIC_1001)));
    }

    @Override
    public Music findOne(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Entry.of(ALBUM_1000)));
    }

    @Override
    public Music update(Music entity) {
        var id = entity.getId();
        var stored = repository.findById(id);

        if (stored.isPresent()) {
            var targetEntity = stored.get();
            targetEntity.setName(targetEntity.getName());
            targetEntity.setUrl(targetEntity.getUrl());
            targetEntity.setSource(targetEntity.getSource());

            return repository.save(targetEntity);
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
}
