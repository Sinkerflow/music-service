package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class AlbumNotFoundException extends ResourceNotFoundException {

    public AlbumNotFoundException(Entry entry) {
        super(entry);
    }
}
