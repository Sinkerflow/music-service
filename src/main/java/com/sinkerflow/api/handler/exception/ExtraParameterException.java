package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;

public class ExtraParameterException extends SinkerException {

    public ExtraParameterException(Entry entry) {
        super(entry);
    }
}
