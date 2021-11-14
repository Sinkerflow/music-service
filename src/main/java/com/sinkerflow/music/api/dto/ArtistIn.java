package com.sinkerflow.music.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ArtistIn {

    private UUID id;

    @NotNull
    private String name;

    private String description;

    private String avatarUrl;

    @NotNull
    private Set<UUID> albumIds;
}