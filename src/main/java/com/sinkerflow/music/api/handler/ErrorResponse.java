package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;

    private String detail;

    private Map<String, String> extra;

    private Instant date;

    private String trace;
}
