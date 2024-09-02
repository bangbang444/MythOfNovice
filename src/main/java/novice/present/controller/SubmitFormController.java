package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.ItemInfo;
import novice.present.domain.SearchLog;
import novice.present.domain.User;
import novice.present.domain.gpt.dto.PresentRecommendation;
import novice.present.repository.SubmitFormRepositoryImpl;
import novice.present.service.GptService;
import novice.present.service.SearchLogService;
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
    private final SearchLogService searchLogService;

    @GetMapping("/submitForm")
    public String present(Model model, User user){
        log.info("선물 조건 입력 화면 출력");
        model.addAttribute("user", user);
        model.addAttribute("itemInfo", new ItemInfo());
        return "submitForm";
    }

    //메인 화면에서 선물 정보가 넘어올때 사용하는 매핑
    @PostMapping("/submitForm")
    public String present(@Validated @ModelAttribute ItemInfo itemInfo, BindingResult bindingResult, Model model, User user) {
        log.info(itemInfo.toString());
        model.addAttribute("user", user);


        //타입 오류가 발생했을때
        if(bindingResult.hasErrors()){
            log.info(bindingResult.getAllErrors().toString());
            return "submitForm";
        }


        // GPT에게 질의 후 쿠팡 URL 생성
        String prompt = generatePrompt(itemInfo);
        List<PresentRecommendation> recommendedItems = gptService.questionAndAnswering(prompt, itemInfo.getPrice());

        //검색기록 저장로직
        if (user != null) {
            String recommendedItemName = "";
            String recommendedItemUrl = "";
            for (PresentRecommendation recommendedItem : recommendedItems) {
                recommendedItemName += recommendedItem.getPresentName()+"\n";
                recommendedItemUrl += recommendedItem.getUrl() + "\n";
            }

            SearchLog searchLog = makeSearchLog(itemInfo.getGender(), itemInfo.getAge(), itemInfo.getRelationship(), itemInfo.getPrice(), itemInfo.getSituation(),
                    itemInfo.getCategory(), itemInfo.getOption(), recommendedItemName, recommendedItemUrl, user);
            searchLogService.addSearchLog(searchLog);
        }

        // 쿠팡 URL을 모델에 담아 리다이렉트
        model.addAttribute("recommendedItems", recommendedItems);
        return "responsePage";
    }

    private SearchLog makeSearchLog(String gender, Integer age, String relationship, Integer price, String situation,
                               String category, String option, String recommendedItemName, String recommendedItemUrl, User user) {
        SearchLog searchLog = new SearchLog();
        searchLog.setInputGender(gender);
        searchLog.setInputAge(age);
        searchLog.setInputRelationship(relationship);
        searchLog.setInputPrice(price);
        searchLog.setInputSituation(situation);
        searchLog.setInputCategory(category);
        searchLog.setInputOption(option);
        searchLog.setOutputName(recommendedItemName);
        searchLog.setOutputUrl(recommendedItemUrl);
        searchLog.setUser(user);
        return searchLog;
    }

    private String generatePrompt(ItemInfo itemInfo) {
        return "성별: " + itemInfo.getGender() + ", 나이: " + itemInfo.getAge() +
                ", 관계: " + itemInfo.getRelationship() + ", 가격대: " + itemInfo.getPrice() +
                ", 상황: " + itemInfo.getSituation() + ", 카테고리: " + itemInfo.getCategory();
    }
}
