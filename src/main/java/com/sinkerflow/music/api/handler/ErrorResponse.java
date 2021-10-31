package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;

    private String detail;

    private OffsetDateTime date;

    private String trace;
}
