package com.example.baitapbuoi2.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.baitapbuoi2.service.OrderService;

@Configuration
public class OrderServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "order.service.enabled", havingValue = "true")
    public OrderService orderService() {
        return new OrderService();
    }
}