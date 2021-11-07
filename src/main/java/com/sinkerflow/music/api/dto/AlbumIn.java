package com.sinkerflow.music.api.dto;

import com.sinkerflow.music.dao.model.Audit;
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
public class AlbumIn {

    private UUID id;

    private String name;

    private String description;

    private String type;

    private String coverUrl;

    private Instant released;

    private Audit audit;
}
