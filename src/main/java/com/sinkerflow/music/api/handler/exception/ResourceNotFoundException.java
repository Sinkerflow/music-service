package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class ResourceNotFoundException extends SinkerException {

    public ResourceNotFoundException(Entry entry) {
        super(entry);
    }
}
