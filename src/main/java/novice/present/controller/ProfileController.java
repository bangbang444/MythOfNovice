package novice.present.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping("/profile")
    public String profileTest(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = null;
        log.info("메인 컨트롤러: {}", session);
        if (session != null) {
            user = (User) session.getAttribute(AuthController.LOGIN_MEMBER);
            log.info("메인 컨트롤러 유저: {}", user);
        }

        //유저가 로그인하지 않았으면 url로 접근해도 로그인화면으로
        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "userProfile";
    }
}
