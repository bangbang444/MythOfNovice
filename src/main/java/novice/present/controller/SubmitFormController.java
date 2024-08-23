package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.ItemInfo;
import novice.present.repository.SubmitFormRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SubmitFormController {

    private final SubmitFormRepositoryImpl submitFormRepository;

    @GetMapping("/submitForm")
    public String present(Model model){
        model.addAttribute("itemInfo", new ItemInfo());
        return "submitForm";
    }

    //메인 화면에서 선물정보가 넘어올때 사용하는 매핑
    @PostMapping("/submitForm")
    public String present(@ModelAttribute ItemInfo itemInfo) {
        log.info(itemInfo.toString());
        ItemInfo saved = submitFormRepository.save(itemInfo);
        log.info(saved.toString());
        return "redirect:/";
    }
}
