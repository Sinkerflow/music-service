package com.sinkerflow.music.dao.model;

import com.sinkerflow.music.dao.model.type.AlbumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document
public class Album {

    @Id
    private UUID id;

    private String url;

    private String name;

    private String description;

    private AlbumType type;

    private String coverUrl;

    private Instant released;

    private Audit audit;
}
