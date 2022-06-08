package com.sinkerflow.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String url;

    private String name;

    private String description;

    private UUID avatarId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artists_albums", joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")})
    private Set<AlbumEntity> albums = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artists_tracks", joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id")})
    private Set<TrackEntity> tracks = new HashSet<>();

    @Embedded
    private AuditEntity audit;
}
