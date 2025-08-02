package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.UUID;

@Component
public class FilterUtility {
    private static final Logger logger = LoggerFactory.getLogger(FilterUtility.class);
    
    public static final String CORRELATION_ID = "carrental-correlation-id";
    public static final String CORRELATION_ID_HEADER_LOWERCASE = CORRELATION_ID.toLowerCase();

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders == null) {
            logger.warn("Request headers are null");
            return null;
        }
        
        // Check both exact and lowercase versions of the header
        String correlationId = requestHeaders.getFirst(CORRELATION_ID);
        if (correlationId == null) {
            correlationId = requestHeaders.getFirst(CORRELATION_ID_HEADER_LOWERCASE);
        }
        
        logger.debug("Retrieved correlation ID from headers: {}", correlationId);
        return correlationId;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        if (exchange == null || name == null || value == null) {
            logger.warn("Invalid parameters to setRequestHeader - exchange: {}, name: {}, value: {}", 
                exchange != null ? "not null" : "null", 
                name, 
                value);
            return exchange;
        }
        
        logger.debug("Setting header '{}' to '{}' for request to: {}", 
            name, value, exchange.getRequest().getPath());
            
        return exchange.mutate()
            .request(builder -> builder.headers(headers -> headers.set(name, value)))
            .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        logger.info("Setting correlation ID: {}", correlationId);
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
    
    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

}