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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", null);
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user, BindingResult bindingResult , RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "register";
        }

        userRepository.save(user);
        log.info(user.toString());

        return "index";
    }

    @GetMapping("/present")
    public String present(Model model){
        return "submitForm";
    }

    //메인 화면에서 선물정보가 넘어올때 사용하는 매핑
    @PostMapping("/present")
    public String present() {
        return "redirect:/index";
    }

}
