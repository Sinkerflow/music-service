package com.sinkerflow.api.model.type;

import lombok.Getter;

@Getter
public enum FileType {
    UNKNOWN,
    ALBUM, // album coverage
    ARTIST, // artist avater
    TRACK // for example mp3, vaw and etc.
}
