package com.rzdp.fortressgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("Adding the correlation ID to the outbound headers. {}",
                filterUtils.getCorrelationId());
        ctx.getResponse()
                .addHeader(FilterUtils.HEADER_CORRELATION_ID_KEY,
                        filterUtils.getCorrelationId());
        log.debug("Completing outgoing request for {}.",
                ctx.getRequest().getRequestURI());
        return null;
    }
}
