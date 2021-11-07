package com.sinkerflow.music.api.handler;

import com.sinkerflow.music.api.handler.exception.MultipleErrorsException;
import com.sinkerflow.music.api.handler.exception.SinkerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;

@RestControllerAdvice
public class CustomErrorHandler {

    @Value("${STACKTRACE_MODE_ENABLED}")
    private boolean isStackTraceMode;

    @ExceptionHandler(MultipleErrorsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MultipleErrorsResponse resourceNotFoundException(MultipleErrorsException e) {
        var exceptions = e.getExceptions();
        var errors = new HashSet<ErrorResponse>();

        for (SinkerException exception : exceptions) {
            Entry entry = exception.getEntry();
            BusinessCode businessCode = entry.getBusinessCode();
            var error = ErrorResponse.builder()
                    .code(businessCode.getCode())
                    .message(businessCode.getMessage())
                    .detail(entry.getDetail())
                    .date(Instant.now())
                    .extra(Collections.emptyMap())
                    .trace(isStackTraceMode ? e.getStackTrace()[0].toString() : null)
                    .build();
            errors.add(error);
        }
        return new MultipleErrorsResponse(errors);
    }
}
