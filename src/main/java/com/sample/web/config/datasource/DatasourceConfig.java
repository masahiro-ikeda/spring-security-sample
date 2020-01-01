package com.sample.web.config.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName("com.mysql.jdbc.Driver");
		dataSourceConfig.setJdbcUrl("jdbc:mysql://localhost/develop");
		dataSourceConfig.setUsername("dev-user");
		dataSourceConfig.setPassword("dev-pass");

		return new HikariDataSource(dataSourceConfig);
	}
}
