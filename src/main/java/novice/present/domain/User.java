package novice.present.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity // JPA 엔티티 클래스임을 나타냅니다.
@Table(name = "users") // 매핑될 테이블명을 설정합니다.
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * [로그인 정보]
     * userLoginId: 최대 10자, 중복 안됨.
     * userPassword: 숫자, 영어, 특수문자 필수 & 8자 이상 & Hash로 저장 및 비교
     */

    @Column(unique = true, length = 10)
    private String userLoginId;

    @Column(nullable = false)
    private String userPassword;
    @Transient
    private String userRepeatPassword;

    /**
     * [기타 정보]
     * userNickname: 최대 10자, 중복 안됨.
     * userJob:
     * userEmail: 이메일 형식. 중복 안됨.
     * userGender: DB 저장 형식 - M or F
     * userBirth:
     * year :
     * month :
     * day :
     */

    @Column(unique = true, length = 10, nullable = false)
    private String userNickname;
    @Column(length = 50)
    private String userJob;

    @Email
    @Column(nullable = false, unique = true)
    private String userEmail;

    @Enumerated(EnumType.STRING) // Enum 타입의 성별을 문자열로 저장합니다.
    @Column(length = 1) // 성별은 "M" 또는 "F"로 저장됩니다.
    private Gender userGender;

    @Column
    private LocalDate userBirth;

    @Transient
    private int year;
    @Transient
    private int month;
    @Transient
    private int day;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SearchLog> logList = new ArrayList<>();

    public User(String userLoginId, String userPassword, String userNickname, String userJob, String userEmail, Gender userGender, int year, int month, int day) {
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userJob = userJob;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userBirth = LocalDate.of(year, month, day);
    }

    // Enum 타입의 성별을 정의합니다.
    public enum Gender {
        M, F
    }
}
