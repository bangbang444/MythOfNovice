package novice.present.domain.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PresentRecommendation {
    private String presentName;
    private String url;
}
