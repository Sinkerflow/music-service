package com.sinkerflow.music.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document
public class Artist {

    @Id
    private UUID id;

    private String url;

    private String name;

    private String description;

    private String avatarUrl;

    private Set<UUID> albumIds;

    private Audit audit;
}
