package com.sample.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class NoticeAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {

        // 認証処理
        Authentication auth = super.authenticate(authentication);

        // 認証情報の取得
        LoginUserModel user = (LoginUserModel) auth.getPrincipal();


        return auth;
    }
}
