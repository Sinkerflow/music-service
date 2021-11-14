package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class ResourceAlreadyExistsException extends SinkerException {

    public ResourceAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
