package novice.present.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    // 기본키
    private Long userId;

    /**
     * [로그인 정보]
     * userLoginId: 최대 10자, 중복 안됨.
     * userPassword: 숫자, 영어, 특수문자 필수 & 8자 이상 & Hash로 저장 및 비교
     */
    private String userLoginId;
    private String userPassword;

    /**
     * [기타 정보]
     * userNickname: 최대 10자, 중복 안됨.
     * userJob:
     * userEmail: 이메일 형식
     * userGender: DB 저장 형식 - M or F
     * uesrBirth:
     */
    private String userNickname;
    private String userJob;
    private String userEmail;
    private String userGender;
    private LocalDate userBirth;
}
