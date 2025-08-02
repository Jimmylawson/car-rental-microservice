package com.example.gateway.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public RouteLocator testRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("test-route", r -> r.path("/test/**")
                        .filters(f -> f.rewritePath("/test/(?<segment>.*)", "/${segment}"))
                        .uri("http://httpbin.org:80"))
                .build();
    }
}
