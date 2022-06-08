package com.sinkerflow.api.handler;

import com.sinkerflow.api.handler.exception.MultipleErrorsException;
import com.sinkerflow.api.handler.exception.SinkerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class CustomErrorHandler {

//    @Value("${STACKTRACE_MODE_ENABLED}")
//    private boolean isStackTraceMode;

    @ExceptionHandler(MultipleErrorsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MultipleErrorsResponse multipleExceptionHandler(MultipleErrorsException e) {
        List<Entry> entries = e.getEntries();
        List<ErrorResponse> errors = new ArrayList<>();

        for (Entry entry : entries) {
            BusinessCode businessCode = entry.getBusinessCode();
            ErrorResponse error = ErrorResponse.builder()
                    .code(businessCode.getCode())
                    .message(businessCode.getMessage())
                    .debugDetail(entry.getDebugDetail())
                    .timestamp(Instant.now())
                    .extra(entry.getExtra())
                    //.trace(isStackTraceMode ? e.getStackTrace()[0].toString() : null)
                    .trace(null)
                    .build();
            errors.add(error);
        }
        return new MultipleErrorsResponse(errors);
    }

    @ExceptionHandler(SinkerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MultipleErrorsResponse singleExceptionHandler(SinkerException e) {
        BusinessCode businessCode = e.getEntry().getBusinessCode();
        ErrorResponse error = ErrorResponse.builder()
                .code(businessCode.getCode())
                .message(businessCode.getMessage())
                .debugDetail(e.getEntry().getDebugDetail())
                .timestamp(Instant.now())
                .extra(Collections.emptyMap())
                //.trace(isStackTraceMode ? e.getStackTrace()[0].toString() : null)
                .trace(null)
                .build();
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(error);
        return new MultipleErrorsResponse(errors);
    }
}
