package com.example.gateway.controller;

import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GatewayController {

    private final Map<String, GatewayFilterFactory<?>> filterFactories;

    public GatewayController(Map<String, GatewayFilterFactory<?>> filterFactories) {
        this.filterFactories = filterFactories;
    }

    @GetMapping("/filters")
    public Map<String, String> listFilters() {
        return filterFactories.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getClass().getSimpleName()
                ));
    }
}
