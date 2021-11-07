package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class AlbumAlreadyExistsException extends SinkerException {

    public AlbumAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
