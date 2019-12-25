package com.sample.config.datasource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
@Configuration
public class mysqlConfig {

    @Bean
    public DataSource mysqlDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:13306/develop");
        dataSourceBuilder.username("dev-user");
        dataSourceBuilder.password("dev-pass");

        return dataSourceBuilder.build();
    }
}
