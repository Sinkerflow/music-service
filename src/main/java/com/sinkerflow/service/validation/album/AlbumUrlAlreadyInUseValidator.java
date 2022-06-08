package com.sinkerflow.service.validation.album;

import com.sinkerflow.api.model.AlbumIn;
import com.sinkerflow.api.handler.BusinessCode;
import com.sinkerflow.api.handler.Entry;
import com.sinkerflow.dao.repository.AlbumRepository;
import com.sinkerflow.service.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.sinkerflow.api.handler.BusinessCode.ALBUM_1002;

@AllArgsConstructor
@Component
public class AlbumUrlAlreadyInUseValidator extends Validator<AlbumIn> {

    private AlbumRepository albumRepository;

    @Override
    public List<Entry> validate(AlbumIn model) {
        if (model.getUrl() != null && albumRepository.existsByUrl(model.getUrl())) {
            return List.of(Entry.builder()
                            .businessCode(ALBUM_1002)
                            .debugDetail(String.format("Album by the URL '%s' is already in use", model.getUrl()))
                            .extra(Map.of("field", "url"))
                    .build());
        }

        return Collections.emptyList();
    }
}
