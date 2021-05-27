package com.icuxika.scaffold.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean("redisConnectionFactory")
    @Primary
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        configuration.setPassword("ALLURE_love921");
        configuration.setDatabase(0);
        return new LettuceConnectionFactory(configuration);
    }

    @Bean("redisConnectionFactory1")
    public LettuceConnectionFactory redisConnectionFactory1() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        configuration.setPassword("ALLURE_love921");
        configuration.setDatabase(1);
        return new LettuceConnectionFactory(configuration);
    }
}
