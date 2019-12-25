package com.sample.config.secutiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * セッション管理に関する設定
 *
 * - 保存先
 * @see com.sample.config.datasource.RedisConfig
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
