package com.sinkerflow.music.service.logic;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.Entry;
import com.sinkerflow.music.api.handler.exception.ResourceAlreadyExistsException;
import com.sinkerflow.music.api.handler.exception.ArtistNotFoundException;
import com.sinkerflow.music.dao.model.Artist;
import com.sinkerflow.music.dao.repository.ArtistRepository;
import com.sinkerflow.music.helper.TokenHelper;
import com.sinkerflow.music.service.ArtistService;
import com.sinkerflow.music.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistServiceLogic implements ArtistService {

    private final ArtistRepository repository;
    private final AuditService auditService;

    @Override
    public Artist create(Artist entity) {
        var id = entity.getId();

        if (Objects.nonNull(id)) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.ARTIST_1003));
        }
        entity.setId(TokenHelper.generate());

        if (repository.existsByName(entity.getName())) {
            throw new ResourceAlreadyExistsException(Entry.of(BusinessCode.ARTIST_1002));
        }
        return repository.save(entity);
    }

    @Override
    public List<Artist> find() {
        return repository.findAll();
    }

    @Override
    public Collection<Artist> find(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public Artist findOne(UUID artistId) {
        return repository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1000)));
    }

    @Override
    public Artist findOne(String artistName) {
        return repository.findByName(artistName)
                .orElseThrow(() -> new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1001)));
    }

    @Override
    public Artist update(Artist artist) {
        var id = artist.getId();
        var stored = repository.findById(id);

        if (stored.isPresent()) {
            var entity = stored.get();
            entity.setName(artist.getName());
            entity.setDescription(artist.getDescription());
            entity.setAlbumIds(artist.getAlbumIds());
            entity.setAudit(auditService.update(entity.getAudit()));

            return repository.save(entity);
        } else {
            throw new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1000));
        }
    }

    @Override
    public void delete(UUID artistId) {
        if (!repository.existsById(artistId))
            throw new ArtistNotFoundException(Entry.of(BusinessCode.ARTIST_1001));
        repository.deleteById(artistId);
    }
}
