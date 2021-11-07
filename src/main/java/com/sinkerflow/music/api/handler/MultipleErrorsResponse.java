package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
public class MultipleErrorsResponse {

    private Collection<ErrorResponse> errors;
}
