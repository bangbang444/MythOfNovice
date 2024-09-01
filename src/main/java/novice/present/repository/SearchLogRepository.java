package novice.present.repository;

import novice.present.domain.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
    // 특정 유저의 검색 기록을 조회하는 메서드
    List<SearchLog> findByUserUserId(Long userId);

    // 특정 유저의 즐겨찾기된 검색 기록만 조회하는 메서드
    List<SearchLog> findByUserUserIdAndIsBookmarkedTrue(Long userId);


}
