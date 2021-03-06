package com.sample.authentication.security;

import com.sample.application.controller.SampleController;
import com.sample.common.dao.entity.User;
import com.sample.common.dao.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger("com.sample.authentication.security");

    private UserRepository userRepository;

    @Autowired
    LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("LoginId: " + username);

        // nullチェック
        if (StringUtils.isEmpty(username)) {
            logger.info("userId is empty.");
            throw new UsernameNotFoundException("userId is empty.");
        }

        // ユーザ取得
        Optional<User> result = userRepository.findById(username);
        if (result.isPresent()) {
            User user = result.get();

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

            return new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), authorities);

        } else {
            // Userが取得不可なら例外スロー
            logger.info("Specified User is not exists.");
            throw new UsernameNotFoundException("Specified User is not exists.");
        }
    }
}
