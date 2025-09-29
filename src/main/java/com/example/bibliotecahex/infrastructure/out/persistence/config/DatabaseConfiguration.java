package com.example.bibliotecahex.infrastructure.out.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void configureDbConfig() {
        DbConfig.setDataSource(dataSource);
    }
}