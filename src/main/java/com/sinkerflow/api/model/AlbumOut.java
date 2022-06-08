package com.sinkerflow.api.model;

import com.sinkerflow.api.model.type.AlbumType;
import com.sinkerflow.dao.entity.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AlbumOut {

    private UUID id;

    private String url;

    private String name;

    private String description;

    private AlbumType type;

    private UUID coverId;

    private Instant released;

    private List<ArtistOut> artists = new ArrayList<>();

    private AuditEntity audit;
}
