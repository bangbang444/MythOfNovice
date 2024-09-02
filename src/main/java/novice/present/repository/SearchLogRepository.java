package novice.present.repository;

import novice.present.domain.SearchLog;
import novice.present.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
    // 특정 유저의 검색 기록을 조회하는 메서드
    List<SearchLog> findByUserUserId(Long userId);

    // 특정 유저의 즐겨찾기된 검색 기록만 조회하는 메서드
    List<SearchLog> findByUserUserIdAndIsBookmarkedTrue(Long userId);

    // isBookmarked 상태를 업데이트하는 메서드
    @Modifying
    @Transactional
    @Query("UPDATE SearchLog sl SET sl.isBookmarked = :isBookmarked WHERE sl.logId = :logId AND sl.user.userId = :userId")
    void updateBookmarkStatus(Long logId, Boolean isBookmarked, Long userId);



}
