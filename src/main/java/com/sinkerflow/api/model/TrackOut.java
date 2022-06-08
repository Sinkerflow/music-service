package com.sinkerflow.api.model;

import com.sinkerflow.dao.entity.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TrackOut {

    private UUID id;

    private String url;

    private String name;

    private UUID artistId;

    private AuditEntity audit;
}
