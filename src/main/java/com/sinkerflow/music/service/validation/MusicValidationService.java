package com.sinkerflow.music.service.validation;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.Entry;
import com.sinkerflow.music.api.handler.exception.AlbumAlreadyExistsException;
import com.sinkerflow.music.api.handler.exception.ExtraParameterException;
import com.sinkerflow.music.api.handler.exception.MultipleErrorsException;
import com.sinkerflow.music.api.handler.exception.SinkerException;
import com.sinkerflow.music.dao.model.Album;
import com.sinkerflow.music.dao.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MusicValidationService extends AbstractValidationService<Album> {

    private final AlbumRepository repository;

    @Override
    public void validateOnCreate(Album entity) {
        Collection<SinkerException> errors = new HashSet<>();

        if (Objects.nonNull(entity.getUrl()) && repository.findByUrl(entity.getUrl()).isPresent()) {
            errors.add(new AlbumAlreadyExistsException(Entry.of(BusinessCode.ALBUM_1002)));
        }

        if (Objects.nonNull(entity.getId())) {
            errors.add(new ExtraParameterException(Entry.of(BusinessCode.SINKER_1001)));
        }

        if (!errors.isEmpty())
            throw new MultipleErrorsException(errors);
    }

    @Override
    public void validateOnUpdate(Album entity) {
        Collection<SinkerException> errors = new HashSet<>();

        var id = entity.getId();
        var album = repository.findById(id);

        if (album.isPresent()) {
            if (Objects.nonNull(entity.getUrl()) && !entity.getUrl().equals(album.get().getUrl())) {
                if (repository.findByUrl(entity.getUrl()).isPresent()) {
                    errors.add(new AlbumAlreadyExistsException(Entry.of(BusinessCode.ALBUM_1002)));
                }
            }
        }

        if (!errors.isEmpty())
            throw new MultipleErrorsException(errors);
    }
}
