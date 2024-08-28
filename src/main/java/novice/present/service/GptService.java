package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.gpt.dto.GptRequest;
import novice.present.domain.gpt.dto.GptResponse;
import novice.present.domain.gpt.dto.PresentRecommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GptService {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public List<PresentRecommendation> questionAndAnswering(String prompt, int userPrice) {
        String condition = "다음 정보를 바탕으로 3~5개의 추천 선물을 성별을 고려해야하는 선물에는 성별을 명시해주고, 번호 없이 이름만으로 구성된 리스트 형태로 제공해줘. 예를 들어:\n아이템1\n아이템2\n...";

        GptRequest request = new GptRequest(
                model, condition, prompt, 1, 256, 1, 2, 2);

        GptResponse gptResponse = restTemplate.postForObject(
                apiUrl, request, GptResponse.class
        );

        String gptAnswer = gptResponse.getChoices().get(0).getMessage().getContent();
        log.info("gpt question 성공: gptAnswer={}", gptAnswer);

        return extractRecommendedPresentsWithUrls(gptAnswer, userPrice);
    }


    private List<PresentRecommendation> extractRecommendedPresentsWithUrls(String gptAnswer, int userPrice) {
        List<PresentRecommendation> presents = new ArrayList<>();
        String[] lines = gptAnswer.split("\n");

        // Alpha 값을 설정하여 최소 및 최대 가격 범위 설정 (예: ±20%의 범위)
        int alpha = (int) (userPrice * 0.2);
        int minPrice = userPrice - alpha;
        int maxPrice = userPrice + alpha;

        for (String line : lines) {
            String presentName = line.trim();
            if (!presentName.isEmpty()) {
                String url = generateCoupangUrl(presentName, minPrice, maxPrice);
                presents.add(new PresentRecommendation(presentName, url));
            }
            if (presents.size() == 5) {
                break;
            }
        }
        log.info("생성된 추천 선물 리스트: {}", presents);

        return presents;
    }



    private String generateCoupangUrl(String presentName, int minPrice, int maxPrice) {
        String encodedPresentName = URLEncoder.encode(presentName, StandardCharsets.UTF_8);
        return "https://www.coupang.com/np/search?rocketAll=false&q=" + encodedPresentName +
                "&priceRange=" + minPrice + "&minPrice=" + minPrice + "&maxPrice=" + maxPrice;
    }
}
