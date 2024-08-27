package novice.present.validation;

import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UserValidator implements Validator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        // userLoginId 검증 - 최대 10자, 중복 안됨
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLoginId", "signup.userLoginId");
        if(user.getUserLoginId().length() > 10){
            log.info("1");
            errors.rejectValue("userLoginId", "signup.userLoginId.length");
        }

        // 패스워드
        if(user.getUserPassword().isEmpty()){
            errors.rejectValue("userPassword", "signup.password"); //errorCode: pattern
        } //공백 방지
        else if (!Pattern.matches(PASSWORD_PATTERN, user.getUserPassword())) {
            log.info("2");
            errors.rejectValue("userPassword", "signup.password.condition");
        } // userPassword 검증 - 숫자, 영어, 특수문자 필수 & 8자 이상 & Hash로 저장 및 비교
        else if (!user.getUserPassword().equals(user.getUserRepeatPassword())) {
            log.info("3");
            errors.rejectValue("userPassword", "signup.password.match", "패스워드가 일치하지 않습니다.");
        } // userPassword와 userRepeatPassword 일치하는지 확인

        // 이메일
        if(user.getUserEmail().isEmpty()){
            errors.rejectValue("userEmail", "signup.email");
        }

        // 닉네임
        if(user.getUserNickname().isEmpty()){
            errors.rejectValue("userNickname", "signup.nickname");
        }

        // 성별
        if(user.getUserGender() == null){
            errors.rejectValue("userGender", "signup.gender");
        }

        // 직업
        if(user.getUserJob().isEmpty()){
            errors.rejectValue("userJob", "signup.job");
        }

    }
}
