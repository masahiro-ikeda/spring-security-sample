package com.sample.config.secutiry;

import com.sample.security.NoticeAuthenticationProvider;
import com.sample.security.NoticeAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Spring Security設定.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private NoticeAuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .mvcMatchers("/home").hasAuthority("USER")
                .mvcMatchers("/select").authenticated();

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/security/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/select", true)
                .permitAll();

        // // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/security/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public NoticeAuthenticationProvider authenticationProvider() throws Exception {
        NoticeAuthenticationProvider provider = new NoticeAuthenticationProvider();

        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(authenticationService);

        return provider;
    }
}
