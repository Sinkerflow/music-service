package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class ArtistAlreadyExistsException extends SinkerException {

    public ArtistAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
