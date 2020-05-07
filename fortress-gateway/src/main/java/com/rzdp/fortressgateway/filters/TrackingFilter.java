package com.rzdp.fortressgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(TrackingFilter.class);
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private final FilterUtils filterUtils;

    @Autowired
    public TrackingFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() {
        String correlationId = filterUtils.getCorrelationId();
        if (correlationId != null) {
            log.debug("x-correlation-id found in tracking filter: [{}]", correlationId);
        } else {
            filterUtils.setCorrelationId(UUID.randomUUID().toString());
            correlationId = filterUtils.getCorrelationId();
            log.debug("x-correlation-id generated in tracking filter: [{}]", correlationId);
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("Processing incoming request for: [{}]", ctx.getRequest().getRequestURI());
        ctx.addZuulRequestHeader(FilterUtils.HEADER_CORRELATION_ID_KEY, correlationId);
        return null;
    }
}
