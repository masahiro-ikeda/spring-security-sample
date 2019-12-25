package com.sample.app;

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
}
