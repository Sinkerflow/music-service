package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class ResourceAlreadyExistsException extends SinkerException {

    public ResourceAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
