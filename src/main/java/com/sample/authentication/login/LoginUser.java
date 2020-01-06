package com.sample.authentication.login;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String password;
	private List<SimpleGrantedAuthority> authorities;

	/**
	 * @param userId
	 * @param userName
	 * @param password
	 * @param authorities
	 */
	LoginUser(String userId, String userName, String password, List<SimpleGrantedAuthority> authorities) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.authorities = authorities;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
