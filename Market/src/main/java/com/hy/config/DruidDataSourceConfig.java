package com.hy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")//将yml文件中的配置以前缀形式配置
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }
}
