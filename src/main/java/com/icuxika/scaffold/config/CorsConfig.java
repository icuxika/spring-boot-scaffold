package com.icuxika.scaffold.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Autowired
    private Framework framework;

    private CorsConfiguration buildConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(framework.getCorsAllowedOrigins().get(0));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        return configuration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
