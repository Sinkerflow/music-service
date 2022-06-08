package com.sinkerflow.service.validation;

import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.service.ValidationService;
import com.sinkerflow.service.validation.album.AlbumUrlAlreadyInUseValidator;
import com.sinkerflow.service.validation.album.AlbumUrlMaxSizeValidator;
import com.sinkerflow.service.validator.AlbumValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class AlbumValidationService implements ValidationService<AlbumIn> {

    private final AlbumUrlMaxSizeValidator albumUrlMaxSizeValidator;
    private final AlbumUrlAlreadyInUseValidator albumUrlAlreadyInUseValidator;

    @Override
    public void onCreate(AlbumIn model) {
        new AlbumValidator()
                .validate(model, albumUrlMaxSizeValidator)
                .validate(model, albumUrlAlreadyInUseValidator)
                .build();
    }

    @Override
    public void onUpdate(AlbumIn entity) {
    }

    @Override
    public void onDelete(UUID id) {
    }
}
