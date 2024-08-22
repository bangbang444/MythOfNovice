package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user, BindingResult bindingResult , RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "signup";
        }

        userRepository.save(user);
        log.info(user.toString());

        return "index";
    }
}
