package novice.present.domain.gpt.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GptRequest {

    private String model;

    private List<GptRequestMessage> messages;

    private double temperature;

    private int max_tokens;

    private double top_p;

    private double frequency_penalty;

    private double presence_penalty;

    public GptRequest(String model,
                      String prompt,
                      String dialog,
                      double temperature,
                      int maxTokens,
                      double topP,
                      double frequencyPenalty,
                      double presencePenalty) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new GptRequestMessage("system",prompt));
        this.messages.add(new GptRequestMessage("user",dialog));
        this.temperature = temperature;
        this.max_tokens = maxTokens;
        this.top_p=topP;
        this.frequency_penalty=frequencyPenalty;
        this.presence_penalty = presencePenalty;
    }
}
