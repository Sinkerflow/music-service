package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class ResourceNotFoundException extends SinkerException {

    public ResourceNotFoundException(Entry entry) {
        super(entry);
    }
}
