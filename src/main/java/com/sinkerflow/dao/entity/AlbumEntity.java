package com.sinkerflow.dao.entity;

import com.sinkerflow.api.model.type.AlbumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String url;

    private String name;

    private String description;

    private AlbumType type;

    private UUID coverId;

    private Instant released;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "albums")
    private Set<ArtistEntity> artists = new HashSet<>();

    @Embedded
    private AuditEntity audit;
}
