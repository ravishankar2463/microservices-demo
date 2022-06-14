package com.example.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@ConfigurationProperties(prefix = "cors")
@Configuration
@Data
@Slf4j
class CorsConfig {
    private Map<String,Registry> allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if(!CollectionUtils.isEmpty(allowedOrigins)) {
                    allowedOrigins.values().forEach(reg -> {
                        log.info("Added CORS exception for api path : " + reg.getPath() + " --(For Instance)--> " +reg.getInstanceUrl());
                        registry.addMapping(reg.getPath()).allowedOrigins(reg.getInstanceUrl());
                    });
                }
            }
        };
    }

    @Data
    static class Registry{
        private String path;
        private String instanceUrl;
    }
}