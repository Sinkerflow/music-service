package com.sinkerflow.music.api.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Entry {

    private BusinessCode businessCode;

    private String detail;

    private Map<String, String> extra;

    public Entry(BusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public Entry(BusinessCode businessCode, String detail) {
        this.businessCode = businessCode;
        this.detail = detail;
    }

    // of
    public static Entry of(BusinessCode businessCode) {
        return new Entry(businessCode);
    }

    public static Entry of(BusinessCode businessCode, String detail) {
        return new Entry(businessCode, detail);
    }

    public static Entry of(BusinessCode businessCode, String detail, Map<String, String> extra) {
        return new Entry(businessCode, detail, extra);
    }
}
