package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.Entry;
import com.sinkerflow.music.api.handler.exception.AlbumNotFoundException;
import com.sinkerflow.music.dao.model.Album;
import com.sinkerflow.music.dao.repository.AlbumRepository;
import com.sinkerflow.music.helper.TokenHelper;
import com.sinkerflow.music.service.AlbumService;
import com.sinkerflow.music.service.AuditService;
import com.sinkerflow.music.service.validation.AlbumValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import static com.sinkerflow.music.api.handler.BusinessCode.ALBUM_1000;
import static com.sinkerflow.music.api.handler.BusinessCode.ALBUM_1001;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumServiceLogic implements AlbumService {

    private final AlbumRepository repository;
    private final AuditService auditService;
    private final AlbumValidationService validation;

    @Override
    public Album create(Album entity) {
        validation.validateOnCreate(entity);

        entity.setId(TokenHelper.generate());
        entity.setAudit(auditService.update(entity.getAudit()));
        return repository.save(entity);
    }

    @Override
    public Collection<Album> find() {
        return repository.findAll();
    }

    @Override
    public Collection<Album> find(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public Album findOne(String url) {
        return repository.findByUrl(url)
                .orElseThrow(() -> new AlbumNotFoundException(Entry.of(ALBUM_1001)));
    }

    @Override
    public Album findOne(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(Entry.of(ALBUM_1000)));
    }

    @Override
    public Album update(Album album) {
        validation.validateOnUpdate(album);

        var stored = repository.findById( album.getId());
        if (stored.isPresent()) {
            var entity = stored.get();
            entity.setName(album.getName());
            entity.setUrl(album.getUrl());
            entity.setDescription(album.getDescription());
            entity.setCoverUrl(album.getCoverUrl());
            entity.setReleased(album.getReleased());
            entity.setAudit(auditService.update(entity.getAudit()));
            return repository.save(entity);
        } else {
            throw new AlbumNotFoundException(Entry.of(ALBUM_1000));
        }
    }

    @Override
    public void delete(UUID albumId) {
        validation.validateOnDelete(albumId);

        if (!repository.existsById(albumId))
            throw new AlbumNotFoundException(Entry.of(BusinessCode.ARTIST_1001));
        repository.deleteById(albumId);
    }
}
