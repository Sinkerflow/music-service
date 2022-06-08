package com.sinkerflow.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MultipleErrorsResponse {

    private List<ErrorResponse> errors;
}
