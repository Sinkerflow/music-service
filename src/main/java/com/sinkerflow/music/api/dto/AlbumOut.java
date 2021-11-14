package com.sinkerflow.music.api.dto;

import com.sinkerflow.music.dao.model.Audit;
import com.sinkerflow.music.dao.model.type.AlbumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    private String coverUrl;

    private Instant released;

    private Audit audit;
}
