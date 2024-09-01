package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.SearchLog;
import novice.present.domain.User;
import novice.present.repository.SearchLogRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchLogService {

    private final SearchLogRepository searchLogRepository;

    // 모든 검색 기록을 가져오는 메서드
    public List<SearchLog> getAllSearchLogs() {
        return searchLogRepository.findAll();
    }

    public List<SearchLog> getSearchLogById(Long id) {
        return searchLogRepository.findByUserUserId(id);
    }

    public List<SearchLog> getSearchLogByIdAndFavorite(Long id) {
        return searchLogRepository.findByUserUserIdAndIsBookmarkedTrue(id);
    }

    public void updateBookmarkStatus(Long logId, Boolean isBookmarked, Long userId){
        searchLogRepository.updateBookmarkStatus(logId, !isBookmarked, userId);
    }
}
