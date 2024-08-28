package novice.present.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping("")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("")
    public String signup(@Validated @ModelAttribute User user, BindingResult bindingResult , RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "signup";
        }

        // 성공 로직
        userRepository.save(user);
        log.info(user.toString());

        return "signup-ok";
    }

    @GetMapping("/ok")
    public String signupOk(){

        return "signup-ok";
    }
}
