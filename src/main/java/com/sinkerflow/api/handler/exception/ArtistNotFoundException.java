package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class ArtistNotFoundException extends SinkerException {

    public ArtistNotFoundException(Entry entry) {
        super(entry);
    }
}
