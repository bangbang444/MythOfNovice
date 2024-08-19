package novice.present.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    //메인 화면에서 선물정보가 넘어올때 사용하는 매핑
    @PostMapping("present")
    public String present() {
        return "redirect:/index";
    }
}
