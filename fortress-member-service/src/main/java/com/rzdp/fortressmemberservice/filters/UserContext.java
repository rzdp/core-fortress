package com.rzdp.fortressmemberservice.filters;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public static final String CORRELATION_ID = "x-correlation-id";

    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
