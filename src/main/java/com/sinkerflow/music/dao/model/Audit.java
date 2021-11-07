package com.sinkerflow.music.dao.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class Audit {

    private Instant createdAt;

    private Instant updatedAt;

    public Audit() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
