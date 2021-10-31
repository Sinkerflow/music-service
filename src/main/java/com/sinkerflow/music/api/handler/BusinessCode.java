package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCode {

    SFA_0001("SFA-0001", "Artist was not found");

    private String code;

    private String message;
}
