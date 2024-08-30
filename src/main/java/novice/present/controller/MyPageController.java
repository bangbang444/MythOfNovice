package novice.present.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("/my-page/log")
    public String searchLog() {
        return "search-log";
    }

    @GetMapping("/my-page/bookmard")
    public String bookmark() {
        return "bookmark";
    }
}
