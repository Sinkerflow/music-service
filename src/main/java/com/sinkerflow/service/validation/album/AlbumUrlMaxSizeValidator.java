package com.sinkerflow.service.validation.album;

import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.service.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class AlbumUrlMaxSizeValidator extends Validator<AlbumIn> {

    @Override
    public List<Entry> validate(AlbumIn model) {
        if (model.getUrl() == null) {
            return Collections.emptyList();
        }

        if (model.getUrl().length() > 50) {
            return List.of(Entry.of(BusinessCode.ALBUM_1003,
                    String.format("URL '%s...' equals %s symbols",
                            model.getUrl().substring(0, 50),
                            model.getUrl().length()),
                    Map.of("field", "url")
            ));
        }
        return Collections.emptyList();
    }
}
