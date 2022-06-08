package com.sinkerflow.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private UUID avatarId;

    private AuditDto audit;
}
