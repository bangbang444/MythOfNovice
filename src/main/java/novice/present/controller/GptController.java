package novice.present.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import novice.present.service.GptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @GetMapping("/question")
    public String gptResponse(@RequestParam("prompt") String prompt){
        return gptService.questionAndAnswering(prompt);
    }

}
