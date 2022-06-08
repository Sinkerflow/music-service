package com.sinkerflow.api.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCode {

    // Common
    SINKER_1001("SINKER-1000", "Parameter ID is not specified"),

    // Artists
    ARTIST_1000("ARTIST-1000", "Artist by this identifier was not found"),
    ARTIST_1001("ARTIST-1001", "Artist by this name was not found"),
    ARTIST_1002("ARTIST-1002", "Artist by this name already exists"),
    ARTIST_1003("ARTIST-1003", "Artist ID must be null when creating"),

    // Albums
    ALBUM_1000("ALBUM-1000", "Album by this identifier was not found"),
    ALBUM_1001("ALBUM-1001", "Album by this URL was not found"),
    ALBUM_1002("ALBUM-1002", "Album by this URL already in use"),
    ALBUM_1003("ALBUM-1003", "Album URL contains more than 50 symbols"),

    // Music
    MUSIC_1000("MUSIC-1000", "Music by identifier was not found"),
    MUSIC_1001("MUSIC-1001", "Music by URL was not found");
//    MUSIC_1002("MUSIC-1002", "Music by URL already exists");

    private String code;

    private String message;
}
