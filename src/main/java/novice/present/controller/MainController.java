package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.domain.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", null);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "register";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model){
        log.info("hi3");
        model.addAttribute("user", null);

        userRepository.save(user);
        log.info(user.toString());

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
