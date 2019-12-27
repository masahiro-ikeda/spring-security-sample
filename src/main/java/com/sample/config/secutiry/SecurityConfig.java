package com.sample.config.secutiry;

import com.sample.security.AuthenticationService;
import com.sample.security.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Spring Security設定.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    SuccessHandler successHandler;

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
                .successHandler(successHandler)
                .permitAll();

        // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/security/logout"));

        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
