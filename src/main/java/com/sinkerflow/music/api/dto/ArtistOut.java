package com.sinkerflow.music.api.dto;

import com.sinkerflow.music.api.dto.AuditDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ArtistOut {

    private UUID id;

    private String url;

    private String name;

    private String description;

    private String avatarUrl;

    private Set<UUID> albumIds;

    private AuditDto audit;
}
