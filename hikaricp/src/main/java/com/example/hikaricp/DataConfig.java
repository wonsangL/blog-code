package com.example.hikaricp;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
