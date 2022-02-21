package com.sinkerflow.music.api.model;

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

    private String url;

    @NotNull
    private String name;

    private UUID artistId;

    @NotNull
    private Collection<Source> sources;
}
