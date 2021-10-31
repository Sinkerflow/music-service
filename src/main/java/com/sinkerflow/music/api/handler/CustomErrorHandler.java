package com.sinkerflow.music.api.handler;

import com.sinkerflow.music.api.handler.exception.ArtistAlreadyExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class CustomErrorHandler {

//    @Value("${STACKTRACE_MODE}")
    private boolean isStackTraceMode;

    @ExceptionHandler(ArtistAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ArtistAlreadyExistsException e, WebRequest request) {
        BusinessCode businessCode = e.getBusinessCode();
        return ErrorResponse.builder()
                .code(businessCode.getCode())
                .message(businessCode.getMessage())
                .detail(e.getDetail())
                .date(OffsetDateTime.now())
                .trace(isStackTraceMode ? Arrays.toString(e.getStackTrace()) : null)
                .build();
    }
}
