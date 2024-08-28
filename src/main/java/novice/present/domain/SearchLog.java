package novice.present.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity // JPA 엔티티 클래스임을 나타냅니다.
@Table(name = "search_logs") // 매핑될 테이블명을 설정합니다.
@NoArgsConstructor
@AllArgsConstructor
public class SearchLog {
    // 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    // 입력 조건
    @Column
    private String inputGender;
    @Column
    private Integer inputAge;
    @Column
    private String inputRelationship;
    @Column
    private Integer inputPrice;
    @Column
    private String inputSituation;
    @Column
    private String inputCategory;
    @Column(columnDefinition = "TEXT")
    private String inputOption;

    // 추천 상품
    @Column
    private String outputName;
    @Column
    private String outputUrl;

    // 즐겨찾기 여부
    @Column(nullable = false)
    private Boolean isBookmarked = false;

    // User 테이블을 외래키로
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
