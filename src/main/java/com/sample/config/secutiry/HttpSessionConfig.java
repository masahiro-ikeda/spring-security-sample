package com.sample.config.secutiry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.security.AuthenticationUser;
import com.sample.security.AuthenticationUserMixin;
import com.sample.security.SessionUser;
import com.sample.security.SessionUserMixin;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * セッション管理に関する設定
 * <p>
 * - 保存先
 *
 * @see com.sample.config.datasource.RedisConfig
 */
@EnableRedisHttpSession
public class HttpSessionConfig implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules( SecurityJackson2Modules.getModules( classLoader ) ); // Spring Securityの拡張モジュールの適用
        objectMapper.addMixIn( AuthenticationUser.class, AuthenticationUserMixin.class );
        objectMapper.addMixIn( SessionUser.class, SessionUserMixin.class );
        return new GenericJackson2JsonRedisSerializer( objectMapper ); // Spring Data RedisにJSON変換用のコンポーネントを適用
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule( new CoreJackson2Module() );
        return mapper;
    }
}
