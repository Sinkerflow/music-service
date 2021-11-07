package com.sinkerflow.music.service.validation;

import com.sinkerflow.music.api.handler.BusinessCode;
import com.sinkerflow.music.api.handler.Entry;
import com.sinkerflow.music.api.handler.exception.AlbumAlreadyExistsException;
import com.sinkerflow.music.api.handler.exception.AlbumNotFoundException;
import com.sinkerflow.music.api.handler.exception.MultipleErrorsException;
import com.sinkerflow.music.api.handler.exception.SinkerException;
import com.sinkerflow.music.dao.model.Album;
import com.sinkerflow.music.dao.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import static com.sinkerflow.music.api.handler.BusinessCode.ALBUM_1000;

@Service
@RequiredArgsConstructor
public class AlbumValidationService extends AbstractValidationService<Album> {

    private final AlbumRepository repository;

    @Override
    public void validateOnCreate(Album entity) {
        Collection<SinkerException> errors = new HashSet<>();

        if (repository.findByUrl(entity.getUrl()).isPresent()) {
            errors.add(new AlbumAlreadyExistsException(Entry.of(BusinessCode.ALBUM_1001)));
        }

        if (Objects.nonNull(entity.getId())) {
            errors.add(new AlbumAlreadyExistsException(Entry.of(ALBUM_1000)));
        }

        if (!errors.isEmpty())
            throw new MultipleErrorsException(errors);
    }

    @Override
    public void validateOnUpdate(Album entity) {
        Collection<SinkerException> errors = new HashSet<>();

        if (repository.findByUrl(entity.getUrl()).isPresent()) {
            errors.add(new AlbumNotFoundException(Entry.of(BusinessCode.ALBUM_1001)));
        }

        if (!errors.isEmpty())
            throw new MultipleErrorsException(errors);
    }
}
