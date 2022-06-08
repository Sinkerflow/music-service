package com.sinkerflow.api.handler.exception;

import com.sinkerflow.api.handler.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MultipleErrorsException extends RuntimeException {

    private List<Entry> entries = new ArrayList<>();
}
