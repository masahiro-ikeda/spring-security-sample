package com.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    SessionUser sessionUser;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        AuthenticationUser user = (AuthenticationUser) authentication.getPrincipal();

        sessionUser.setUserId( user.getUserId() );
        sessionUser.setUserName( user.getUsername() );
        sessionUser.setAuthority( user.getAuthorities().get( 0 ).getAuthority() );
        sessionUser.setFacilityGroup( "testGroup" );

        // リダイレクト先を設定
        response.sendRedirect( "/select" );
    }
}
