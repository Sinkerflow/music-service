package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class AlbumAlreadyExistsException extends SinkerException {

    public AlbumAlreadyExistsException(Entry entry) {
        super(entry);
    }
}
