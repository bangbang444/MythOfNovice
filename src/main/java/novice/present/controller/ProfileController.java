package novice.present.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.service.ProfileService;
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
public class ProfileController {

    private final ProfileService profileService;
    private final UserValidator userValidator;
    
    @InitBinder("user")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

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


    @GetMapping("/profileEdit")
    public String profileEdit(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = null;
        log.info("메인 컨트롤러: {}", session);
        if (session != null) {
            user = (User) session.getAttribute(AuthController.LOGIN_MEMBER);
            log.info("메인 컨트롤러 유저Id: {}", user.getUserLoginId());
        }

        //유저가 로그인하지 않았으면 url로 접근해도 로그인화면으로
        if (session == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "userProfileEdit";
    }

    @PostMapping("/profileEdit")
    public String profileEdit(@ModelAttribute("user") User userUpdate, BindingResult bindingResult, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        User user = null;

        if (bindingResult.hasErrors()) {
            return "userProfileEdit";
        }

        if (session != null) {
            user = (User) session.getAttribute(AuthController.LOGIN_MEMBER);
            log.info("현재유저 정보 ㅣ 로그인아이디 = {}, 사용자명 = {}, 이메일 = {}, 성별 = {}, 직업 = {}",
                    user.getUserLoginId(), user.getUserNickname(), user.getUserEmail(), user.getUserGender(), user.getUserJob());
            log.info("업데이트유저 정보 ㅣ 로그인아이디 = {}, 사용자명 = {}, 이메일 = {}, 성별 = {}, 직업 = {}",
                    userUpdate.getUserLoginId(), userUpdate.getUserNickname(), userUpdate.getUserEmail(), userUpdate.getUserGender(), userUpdate.getUserJob());

            // null이 아닌 정보만 업데이트
            updateUserInfo(userUpdate, user);

            log.info("업데이트후유저 정보 ㅣ 로그인아이디 = {}, 사용자명 = {}, 이메일 = {}, 성별 = {}, 직업 = {}",
                    user.getUserLoginId(), user.getUserNickname(), user.getUserEmail(), user.getUserGender(), user.getUserJob());

            //프로필 업데이트
            session.setAttribute(AuthController.LOGIN_MEMBER, user);

            model.addAttribute(user);
        }



        return "userProfile";
    }

    private static void updateUserInfo(User userUpdate, User user) {
        if (userUpdate.getUserNickname() != null) {
            user.setUserNickname(userUpdate.getUserNickname());
        }
        if (userUpdate.getUserJob() != null) {
            user.setUserJob(userUpdate.getUserJob());
        }
        if (userUpdate.getUserEmail() != null) {
            user.setUserEmail(userUpdate.getUserEmail());
        }
        if (userUpdate.getUserBirth() != null) {
            user.setUserBirth(userUpdate.getUserBirth());
        }
    }
}
