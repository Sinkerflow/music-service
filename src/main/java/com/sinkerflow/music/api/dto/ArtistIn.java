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

    @NotNull(message = "SINKER-0001")
    private String name;

    private String description;

    private String avatarUrl;

    @NotNull(message = "SINKER-0002")
    private Set<UUID> albumIds;
}
