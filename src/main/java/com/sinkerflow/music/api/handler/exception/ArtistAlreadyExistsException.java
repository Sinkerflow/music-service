package com.sinkerflow.music.api.handler.exception;

import com.sinkerflow.music.api.handler.BusinessCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ArtistAlreadyExistsException extends RuntimeException {

    private BusinessCode businessCode;

    private String detail;
}
