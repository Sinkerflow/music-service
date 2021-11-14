package com.sinkerflow.music.api.dto;

import com.sinkerflow.music.dao.model.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MusicIn {

    private UUID id;

    @NotNull
    private String name;

    private String url;

    private UUID artistId;

    @NotNull
    private Collection<Source> source;
}
