package com.sample.app;

import com.sample.security.LoginUserModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        return "select";
    }
}
