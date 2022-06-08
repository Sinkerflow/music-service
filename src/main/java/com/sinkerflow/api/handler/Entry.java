package com.sinkerflow.api.handler;

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

    private String debugDetail;

    private Map<String, String> extra;

    public Entry(BusinessCode businessCode) {
        this.businessCode = businessCode;
    }

    public Entry(BusinessCode businessCode, String debugDetail) {
        this.businessCode = businessCode;
        this.debugDetail = debugDetail;
    }

    // of
    public static Entry of(BusinessCode businessCode) {
        return new Entry(businessCode);
    }

    public static Entry of(BusinessCode businessCode, String debugDetal) {
        return new Entry(businessCode, debugDetal);
    }

    public static Entry of(BusinessCode businessCode, String debugDetal, Map<String, String> extra) {
        return new Entry(businessCode, debugDetal, extra);
    }
}
