package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.ItemInfo;
import novice.present.domain.gpt.dto.PresentRecommendation;
import novice.present.repository.SubmitFormRepositoryImpl;
import novice.present.service.GptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SubmitFormController {

    private final GptService gptService;

    @GetMapping("/submitForm")
    public String present(Model model){
        log.info("선물 조건 입력 화면 출력");
        model.addAttribute("itemInfo", new ItemInfo());
        return "submitForm";
    }

    //메인 화면에서 선물 정보가 넘어올때 사용하는 매핑
    @PostMapping("/submitForm")
    public String present(@Validated @ModelAttribute ItemInfo itemInfo, BindingResult bindingResult, Model model) {
        log.info(itemInfo.toString());

        //타입 오류가 발생했을때
        if(bindingResult.hasErrors()){
            log.info(bindingResult.getAllErrors().toString());
            return "submitForm";
        }


        // GPT에게 질의 후 쿠팡 URL 생성
        String prompt = generatePrompt(itemInfo);
        List<PresentRecommendation> recommendedItems = gptService.questionAndAnswering(prompt, itemInfo.getPrice());

        // 쿠팡 URL을 모델에 담아 리다이렉트
        model.addAttribute("recommendedItems", recommendedItems);
        return "responsePage";
    }

    private String generatePrompt(ItemInfo itemInfo) {
        return "성별: " + itemInfo.getGender() + ", 나이: " + itemInfo.getAge() +
                ", 관계: " + itemInfo.getRelationship() + ", 가격대: " + itemInfo.getPrice() +
                ", 상황: " + itemInfo.getSituation() + ", 카테고리: " + itemInfo.getCategory();
    }
}
