package novice.present.service;

import lombok.RequiredArgsConstructor;
import novice.present.domain.gpt.dto.GptRequest;
import novice.present.domain.gpt.dto.GptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class GptService {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public String questionAndAnswering(String prompt) {
        String condition = "선물 추천해줘";

        // 사용자가 질문한 것을 원하는 데이터로 변환
        GptRequest request = new GptRequest(
                model, condition, prompt, 1,256,1,2,2);

        GptResponse gptResponse = restTemplate.postForObject(
                apiUrl
                , request
                , GptResponse.class
        );

        return gptResponse.getChoices().get(0).getMessage().getContent();


    }
}
