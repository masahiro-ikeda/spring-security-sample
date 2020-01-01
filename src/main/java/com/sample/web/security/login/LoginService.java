package com.sample.web.security.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.common.model.User;
import com.sample.common.repository.UserRepository;

@Service
public class LoginService implements UserDetailsService {

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

		// nullチェック
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("userId is empty.");
		}

		Optional<User> result = userRepository.findById(username);
		if (result.isPresent()) {
			User user = result.get();

			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

			return new LoginUser(
					user.getUserId(),
					user.getUserName(),
					user.getPassword(),
					authorities);
		} else {
			// Userが取得不可なら例外スロー
			throw new UsernameNotFoundException("Specified User is not exists.");
		}
	}
}
