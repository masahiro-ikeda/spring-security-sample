package com.sample.security;

import com.sample.app.model.User;
import com.sample.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {

        // 認証ユーザの取得
        List<User> result = userRepository.findAllById( Arrays.asList( username ) );

        User user = result.get( 0 );
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority( user.getUserRole() ) );

        AuthenticationUser authenticationUser = new AuthenticationUser(
                user.getUserId(), user.getUserName(), user.getPassword(), authorities
        );

        return authenticationUser;
    }
}
