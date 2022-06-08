package com.sinkerflow.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;
import java.time.Instant;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@Embeddable
public class AuditEntity {

    private Instant createdAt;

    private Instant updatedAt;

    public AuditEntity() {
        var currentTime = Instant.now();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }
}
