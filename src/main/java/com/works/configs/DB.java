package com.works.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DB {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean("source")
    public DriverManagerDataSource source() {
        DriverManagerDataSource db = new DriverManagerDataSource();
        db.setDriverClassName(driver);
        db.setUrl(url);
        db.setUsername(username);
        db.setPassword(password);
        return db;
    }


}
