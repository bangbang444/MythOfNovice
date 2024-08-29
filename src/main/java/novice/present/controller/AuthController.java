package novice.present.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.domain.form.LoginForm;
import novice.present.service.AuthService;
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

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {
    public static final String LOGIN_MEMBER = "loginMember";
    private final AuthService authService;
    private final UserValidator userValidator;
    @InitBinder("user")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping("/signup")
    public String signup(Model model){
        log.info("회원가입 화면 호출");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user, BindingResult bindingResult , RedirectAttributes redirectAttributes){
        log.info("회원가입 요청: {}", user.getUserLoginId());
        if (!bindingResult.hasErrors()) {
            authService.signup(user, bindingResult);
        }

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        log.info("로그인 화면 호출");

        model.addAttribute("loginInfo", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Validated @ModelAttribute("loginInfo") LoginForm form, BindingResult bindingResult,
            HttpServletRequest request
    ) {
        log.info("로그인 요청: {}", form.getLoginId());

        if (bindingResult.hasErrors()) {
            log.info("로그인 field error: {}", bindingResult.getFieldErrors());
            return "login";
        }

        User user = authService.login(form.getLoginId(), form.getPassword(), bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("로그인 global error: {}", bindingResult.getGlobalErrors());
            return "login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(LOGIN_MEMBER, user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "forgot-password";
    }
}
