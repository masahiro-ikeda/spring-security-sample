package com.sample.root.config.secutiry;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.common.model.Facility;
import com.sample.root.security.login.LoginUser;
import com.sample.root.security.login.LoginUserMixin;
import com.sample.root.security.session.FacilityMixin;
import com.sample.root.security.session.SessionUser;
import com.sample.root.security.session.SessionUserMixin;

/**
 * セッション管理に関する設定
 * <p>
 * - 保存先
 *
 * @see com.sample.root.config.datasource.RedisConfig
 */
@EnableRedisHttpSession
public class SessionConfig implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();

        // CookieへのアクセスはHTTPプロトコルに限定（SSHとかはNG）
        serializer.setUseHttpOnlyCookie(true);

        // セッションIDをエンコードしないようにする
        serializer.setUseBase64Encoding(false);

        return serializer;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules( SecurityJackson2Modules.getModules( classLoader ) ); // Spring Securityの拡張モジュールの適用
        objectMapper.addMixIn( LoginUser.class, LoginUserMixin.class );
        objectMapper.addMixIn( SessionUser.class, SessionUserMixin.class );
        objectMapper.addMixIn( Facility.class, FacilityMixin.class );
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
