package novice.present.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import novice.present.validation.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String home(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = null;
        log.info("메인 컨트롤러: {}", session);
        if (session != null) {
            user = (User) session.getAttribute(LoginController.LOGIN_MEMBER);
            log.info("메인 컨트롤러 유저: {}", user);
        }

        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/profile")
    public String profileTest() {
        return "userProfile";
    }

}
