package com.sinkerflow.api.model;

import com.sinkerflow.api.model.type.AlbumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AlbumIn {

    private UUID id;

    private String url;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Size(max = 255)
    private String description;

    private AlbumType type;

    private UUID coverId;

    private Instant released;

    @NotNull
    private List<ArtistIn> artists;
}
