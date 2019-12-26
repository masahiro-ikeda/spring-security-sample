package com.sample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class NoticeAuthenticationService implements UserDetailsService {

    public LoginUserModel loadUserByUsername(String username) throws UsernameNotFoundException {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("NON");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return new LoginUserModel(username, "pass", authorities, null);
    }
}
