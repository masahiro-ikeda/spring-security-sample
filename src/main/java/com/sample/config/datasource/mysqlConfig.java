package com.sample.config.datasource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class mysqlConfig {

    @Bean
    public DataSource mysqlDataSource() {

        // 本番ではDriverManagerDataSourceは使わない
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/develop");
        dataSource.setUsername("dev-user");
        dataSource.setPassword("dev-pass");

        return dataSource;
    }
}
