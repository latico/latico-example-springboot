package com.latico.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomZuulConfig {
    @Autowired
    ZuulProperties zuulProperties;

    @Bean
    public CustomRouteLocator dbRouteLocator(DispatcherServletPath server) {
        return new CustomRouteLocator(server.getPrefix(), zuulProperties);
    }

}