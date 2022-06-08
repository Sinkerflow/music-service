package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SinkerException extends RuntimeException {

    private Entry entry;
}
