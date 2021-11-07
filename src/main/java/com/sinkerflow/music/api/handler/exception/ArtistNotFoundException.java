package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class ArtistNotFoundException extends SinkerException {

    public ArtistNotFoundException(Entry entry) {
        super(entry);
    }
}
