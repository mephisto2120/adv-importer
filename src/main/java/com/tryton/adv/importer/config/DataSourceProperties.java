package com.tryton.adv.importer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class DataSourceProperties {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.tomcat.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.tomcat.max-wait}")
    private int maxWait;
    @Value("${spring.datasource.tomcat.max-active}")
    private int maxActive;
    @Value("${spring.datasource.tomcat.max-idle}")
    private int maxIdle;
    @Value("${spring.datasource.tomcat.min-idle}")
    private int minIdle;
}
