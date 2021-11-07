package com.sinkerflow.music.api.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MultipleErrorsException extends RuntimeException {

    private Collection<SinkerException> exceptions;
}
