package com.hy.config;

import com.hy.api.DissApi;
import com.hy.back.DissFallBack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FallBackFactoryConfig {
    @Bean
    public DissFallBack dissFallBack() {
        return new DissFallBack() {};
    }
}
