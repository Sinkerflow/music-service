package com.sinkerflow.music.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document
public class Artist {

    private UUID id;

    private String name;

    private String description;

    private Set<UUID> albumIds;
}
