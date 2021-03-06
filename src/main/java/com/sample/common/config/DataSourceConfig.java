package com.sample.common.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class DataSourceConfig {

	/**
	// データベースの接続設定
	@Bean
	public DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName("com.mysql.jdbc.Driver");
		dataSourceConfig.setJdbcUrl("jdbc:mysql://localhost:13306/develop?useSSL=false");
		dataSourceConfig.setUsername("dev-user");
		dataSourceConfig.setPassword("dev-pass");

		return new HikariDataSource(dataSourceConfig);
	}

	// Redisの接続設定
	@Bean
	public LettuceConnectionFactory connectionFactory() {

		String redisHost = "localhost";
		int redisPort = 16379;

		return new LettuceConnectionFactory(redisHost, redisPort);
	}

	**/
}
