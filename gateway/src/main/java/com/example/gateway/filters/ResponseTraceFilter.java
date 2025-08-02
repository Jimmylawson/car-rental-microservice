package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ResponseTraceFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("ResponseTraceFilter processing request to: {}", exchange.getRequest().getPath());
        
        // Get correlation ID from request headers
        String correlationId = filterUtility.getCorrelationId(exchange.getRequest().getHeaders());
        
        if (correlationId == null) {
            logger.warn("No correlation ID found in request headers");
            return chain.filter(exchange);
        }
        
        logger.info("Will add correlation ID to response: {}", correlationId);
        
        // Create a new ServerWebExchange with a decorated response
        ServerWebExchange mutatedExchange = exchange.mutate()
            .response(new CorrelationIdResponseDecorator(exchange.getResponse(), correlationId))
            .build();
            
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return -10; // Higher precedence than other filters
    }
    
    private static class CorrelationIdResponseDecorator extends ServerHttpResponseDecorator {
        private final String correlationId;

        public CorrelationIdResponseDecorator(ServerHttpResponse delegate, String correlationId) {
            super(delegate);
            this.correlationId = correlationId;
        }


    }
}
