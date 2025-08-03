package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;

import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


//    @Bean
//    public KeyResolver apiResolver() {
//        return exchange -> {
//            // Use the client's IP address as the key for rate limiting
//            String hostAddress = exchange.getRequest().getRemoteAddress() != null ?
//                    exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() :
//                    "unknown";
//            return Mono.just(hostAddress);
//        };
//    }


    }

