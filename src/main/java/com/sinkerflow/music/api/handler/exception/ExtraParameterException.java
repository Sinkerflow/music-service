package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.Entry;

public class ExtraParameterException extends SinkerException {

    public ExtraParameterException(Entry entry) {
        super(entry);
    }
}
