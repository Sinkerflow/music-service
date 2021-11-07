package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCode {

    // Artists
    ARTIST_1000("ARTIST-1000", "Artist by identifier was not found"),
    ARTIST_1001("ARTIST-1001", "Artist by name was not found"),
    ARTIST_1002("ARTIST-1002", "Artist by name already exists"),
    ARTIST_1003("ARTIST-1003", "Artist ID must be null when creating"),

    // Albums
    ALBUM_1000("ALBUM-1000", "Album by identifier was not found"),
    ALBUM_1001("ALBUM-1001", "Album by URL was not found"),
    ALBUM_1002("ALBUM-1001", "Album by URL already exists");

    // Music

    private String code;

    private String message;
}
