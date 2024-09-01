package novice.present.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.SearchLog;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import novice.present.service.SearchLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchLogController {

    private final SearchLogService searchLogService;
    private final UserRepository userRepository;

    @GetMapping("/my-page/log")
    public String searchLog(Model model, User user) {
        //유저가 로그인하지 않았으면 url로 접근해도 로그인화면으로
        if (user == null) {
            return "redirect:/login";
        }

        //유저 정보 모델에 추가
        model.addAttribute("user", user);
        //검색 기록 모델에 추가
        Long userId = user.getUserId();
        log.info("검색을 하는 유저의 아이디 ={}", userId);
        List<SearchLog> searchLogs = searchLogService.getSearchLogById(userId);
        log.info("그 유저의 검색기록 = {}" , searchLogs);
        model.addAttribute("searchLogs", searchLogs);

        log.info("검색 기록 화면 출력");
        model.addAttribute("SearchLog", new SearchLog());
        return "searchLog";

    }
    @PostMapping("/my-page/log")
    public String bookmarkChange(@RequestParam Boolean isBookmarked,
                                 @RequestParam Long logId,
                                 Model model, User user) {
        model.addAttribute("user", user);
        log.info("======isBookmarked = {} logId = {}", isBookmarked, logId);
        searchLogService.updateBookmarkStatus(logId, isBookmarked, user.getUserId());
        List<SearchLog> searchLogById = searchLogService.getSearchLogById(user.getUserId());
        log.info("======"+searchLogById.toString());

        return "redirect:/my-page/log";
    }

    @GetMapping("/my-page/bookmarked")
    public String bookmark(Model model, User user) {
        //유저가 로그인하지 않았으면 url로 접근해도 로그인화면으로
        if (user == null) {
            return "redirect:/login";
        }

        //유저 정보 모델에 추가
        model.addAttribute("user", user);
        //검색 기록 모델에 추가
        Long userId = user.getUserId();
        log.info("검색을 하는 유저의 아이디 ={}", userId);
        List<SearchLog> searchLogs = searchLogService.getSearchLogByIdAndFavorite(userId);
        log.info("그 유저의 검색기록 = {}" , searchLogs);
        model.addAttribute("searchLogs", searchLogs);

        log.info("검색 기록 화면 출력");
        model.addAttribute("SearchLog", new SearchLog());

        return "searchLogFavorite";
    }
    @PostMapping("/my-page/bookmarked")
    public String bookmarkedChange(@RequestParam Boolean isBookmarked,
                                 @RequestParam Long logId,
                                 Model model, User user) {
        model.addAttribute("user", user);
        log.info("======isBookmarked = {} logId = {}", isBookmarked, logId);
        searchLogService.updateBookmarkStatus(logId, isBookmarked, user.getUserId());
        List<SearchLog> searchLogById = searchLogService.getSearchLogById(user.getUserId());
        log.info("======"+searchLogById.toString());

        return "redirect:/my-page/bookmarked";
    }
}
