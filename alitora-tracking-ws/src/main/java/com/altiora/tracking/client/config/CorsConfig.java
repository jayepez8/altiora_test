package com.altiora.tracking.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.altiora.tracking.client.common.TrackingConstants.LOCALHOST_DOCKER_URI;
import static com.altiora.tracking.client.common.TrackingConstants.LOCALHOST_URI;

/**
 * @author jyepez on 7/9/2024
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(LOCALHOST_URI,LOCALHOST_DOCKER_URI)
                        .exposedHeaders("Content-Disposition")
                        .allowedMethods("*");
            }
        };
    }

}