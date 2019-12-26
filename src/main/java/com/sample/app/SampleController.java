package com.sample.app;

import com.sample.security.LoginUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    /**
     * ホーム画面を表示させる
     */
    @GetMapping("home")
    public String showHome() {

        return "home";
    }

    /**
     * 施設選択画面を表示させる
     */
    @GetMapping("select")
    public String showSelect(@AuthenticationPrincipal LoginUserModel loginUserModel) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        loginUserModel.setFacilityId("TEST");

        AbstractAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(loginUserModel,  auth.getCredentials(), auth.getAuthorities());
        newAuth.setDetails(auth.getDetails());

        SecurityContextImpl context = new SecurityContextImpl(newAuth);

        return "select";
    }
}
