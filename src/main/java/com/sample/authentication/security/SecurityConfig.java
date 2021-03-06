package com.sample.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security設定.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	/**
	 * @param loginService
	 * @param loginSuccessHandler
	 */
	@Autowired
	SecurityConfig(LoginService loginService, LoginSuccessHandler loginSuccessHandler) {
		this.userDetailsService = loginService;
		this.authenticationSuccessHandler = loginSuccessHandler;
	}

	/**
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 認可の設定
		http.authorizeRequests()
			.mvcMatchers("/security").permitAll()
			.mvcMatchers("/home", "/select").authenticated();

		// ログイン設定
		http.formLogin()
			.loginProcessingUrl("/security/security")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandler)
			.permitAll();

		// csrf設定
		http.csrf().ignoringAntMatchers("/security/security");
		http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
		
		// ログアウト設定
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/security/logout"));
	}

	/**
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
