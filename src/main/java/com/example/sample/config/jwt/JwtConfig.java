package com.example.sample.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.validity-in-seconds}")
    private long tokenValidityInSeconds;

    @Bean
    public JwtManager jwtManager() {
        return new JwtManager(jwtSecret, tokenValidityInSeconds);
    }
}
