package com.sample.security.login;

import com.sample.app.model.Facility;
import com.sample.app.query.FacilityQuery;
import com.sample.security.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private SessionUser sessionUser;
    private FacilityQuery facilityQuery;

    /**
     * @param sessionUser
     * @param facilityQuery
     */
    @Autowired
    LoginSuccessHandler(SessionUser sessionUser, FacilityQuery facilityQuery) {
        this.facilityQuery = facilityQuery;
        this.sessionUser = sessionUser;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        onAuthenticationSuccess( request, response, authentication );
    }

    /**
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        LoginUser user = (LoginUser) authentication.getPrincipal();

        sessionUser.setUserId( user.getUserId() );
        sessionUser.setUserName( user.getUsername() );
        sessionUser.setAuthority( user.getAuthorities().get( 0 ).getAuthority() );

        List<Facility> facilities
                = facilityQuery.findByUserId( user.getUserId() );
        sessionUser.setFacilities( facilities );

        // リダイレクト先を設定
        response.sendRedirect( "/select" );
    }
}
