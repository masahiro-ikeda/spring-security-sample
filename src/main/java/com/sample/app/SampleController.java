package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SampleController {

    /**
     * ホーム画面を表示させる
     */
    @GetMapping("home")
    public String showHome() {

        return "home";
    }

    @Autowired
    HttpSession session;

    /**
     * 施設選択画面を表示させる
     */
    @GetMapping("select")
    public String showSelect() {

        return "select";
    }
}
