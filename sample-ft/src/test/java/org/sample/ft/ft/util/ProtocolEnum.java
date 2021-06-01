package org.sample.ft.ft.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum ProtocolEnum {
    SOAP,
    REST;
    private static final String PROTOCOL_KEY="protocol";

    private ProtocolEnum(){
    }
    public static ProtocolEnum identify(){
        return from(System.getProperty("protocol"));
    }

    private static ProtocolEnum from(String protocol) {
        return Arrays.stream(values()).filter(e->{
            return StringUtils.equalsIgnoreCase(protocol,e.toString());
        }).findFirst().orElse(SOAP);

    }
}
