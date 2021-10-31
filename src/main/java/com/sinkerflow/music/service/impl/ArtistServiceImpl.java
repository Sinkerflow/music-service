package com.sinkerflow.music.service.impl;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.exception.ArtistAlreadyExistsException;
import com.sinkerflow.music.dao.model.Artist;
import com.sinkerflow.music.dao.repository.ArtistRepository;
import com.sinkerflow.music.helper.TokenHelper;
import com.sinkerflow.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository repository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Artist> findAll() {
        return repository.findAll();
    }

    @Override
    public Artist findOne(UUID artistId) {
        return repository.findById(artistId).orElseThrow(() -> new RuntimeException("Artist was not found"));
    }

    @Override
    public Artist findOne(String artistName) {
        if (!repository.existsByName(artistName)) {
            throw new ArtistAlreadyExistsException(BusinessCode.SFA_0001, null);
        }
        return repository.findByName(artistName);
    }

    @Override
    public Artist saveOrUpdate(Artist artist) {
        UUID id = artist.getId();
        if (Objects.isNull(id)) {
            artist.setId(TokenHelper.generate());
            if (repository.existsByName(artist.getName())) {
                throw new RuntimeException("This name already exists");
            }
            return repository.save(artist);
        }
        if (repository.findById(id).isEmpty()) {
            throw new ArtistAlreadyExistsException(BusinessCode.SFA_0001, null);
        }
        return repository.save(artist);
    }

    @Override
    public void delete(UUID authorId) {
        repository.deleteById(authorId);
    }
}
