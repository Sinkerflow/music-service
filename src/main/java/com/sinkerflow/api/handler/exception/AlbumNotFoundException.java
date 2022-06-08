package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class AlbumNotFoundException extends ResourceNotFoundException {

    public AlbumNotFoundException(Entry entry) {
        super(entry);
    }
}
