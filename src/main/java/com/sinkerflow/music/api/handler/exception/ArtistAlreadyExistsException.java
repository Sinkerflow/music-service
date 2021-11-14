package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class ArtistAlreadyExistsException extends SinkerException {

    public ArtistAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
