package com.sample.application.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redisへの接続設定を保持
 */
@Configuration
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory connectionFactory() {

		String redisHost = "localhost";
		int redisPort = 16379;

		return new LettuceConnectionFactory(redisHost, redisPort);
	}
}
