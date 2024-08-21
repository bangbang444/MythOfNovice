package novice.present.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        log.info("로그인 화면 호출");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin() {
        log.info("로그인 요청");
        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "forgot-password";
    }
}
