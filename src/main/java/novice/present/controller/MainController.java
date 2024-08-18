package novice.present.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {
    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", null);

        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", null);

        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
