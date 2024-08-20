package novice.present.validation;

import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userLoginId", "required");
        if(user.getUserLoginId().length() > 10){
            log.info("1");
            errors.rejectValue("userLoginId", "length", "ID는 1~10자리로 이뤄져야 합니다.");
        }

        // userPassword 검증 - 숫자, 영어, 특수문자 필수 & 8자 이상 & Hash로 저장 및 비교
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "required");
        if (!Pattern.matches(PASSWORD_PATTERN, user.getUserPassword())) {
            log.info("2");
            errors.rejectValue("userPassword", "pattern", "비밀번호는 최소 8자리이며 숫자,영문자,특수문자가 포함되어야 합니다.");
        }

        // userPassword와 userRepeatPassword 일치하는지 확인
        if (!user.getUserPassword().equals(user.getUserRepeatPassword())) {
            log.info("3");
            errors.rejectValue("userRepeatPassword", "match", "패스워드가 일치하지 않습니다.");
        }

    }
}
