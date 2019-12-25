package com.sample.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Spring Security設定.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .mvcMatchers("/home").authenticated();

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/security/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll();

        // // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/security/logout"));
    }
}
