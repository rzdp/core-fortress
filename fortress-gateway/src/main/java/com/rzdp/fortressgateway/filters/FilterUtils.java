package com.rzdp.fortressgateway.filters;

import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";
    public static final String ERROR_FILTER_TYPE = "error";
    public static final String HEADER_CORRELATION_ID_KEY = "x-correlation-id";

    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
