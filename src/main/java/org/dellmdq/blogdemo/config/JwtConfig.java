package org.dellmdq.blogdemo.config;

import org.dellmdq.blogdemo.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
