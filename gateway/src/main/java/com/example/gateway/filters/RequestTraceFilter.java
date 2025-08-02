package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestTraceFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("RequestTraceFilter processing request to: {}", exchange.getRequest().getPath());
        
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String correlationId = filterUtility.getCorrelationId(requestHeaders);

        if (correlationId != null) {
            logger.info("Found correlation ID in request: {}", correlationId);
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
        } else {
            correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            logger.info("Generated new correlation ID: {}", correlationId);
        }

        // Log all request headers for debugging
        logger.info("Request headers after processing: {}", exchange.getRequest().getHeaders());
        
        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    @Override
    public int getOrder() {
        return -100; // This filter should run before the response filter
    }
}