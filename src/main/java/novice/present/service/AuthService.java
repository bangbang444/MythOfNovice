package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(String loginId, String password, BindingResult bindingResult) {
        log.info("로그인 시도: loginId={}", loginId);
        Optional<User> userOptional = userRepository.findByUserLoginIdAndUserPassword(loginId, password);

        if (userOptional.isPresent()) {
            log.info("로그인 성공: loginId={}", loginId);
            return userOptional.get();
        } else {
            log.error("로그인 실패 - 사용자 찾을 수 없음: loginId={}", loginId);
            bindingResult.reject("loginUserNotExist", null, null);
            return null;
        }
    }

    public void signup(User user, BindingResult bindingResult) {

        boolean is_duplicate = false;

        log.info("회원가입 시도: userLoginId={}", user.getUserLoginId());
        if (userRepository.existsByUserLoginId(user.getUserLoginId())) {
            is_duplicate = true;
            bindingResult.rejectValue("userLoginId", "signup.userLoginId.duplicate", "이미 사용 중인 아이디입니다.");
        }

        if (userRepository.existsByUserNickname(user.getUserNickname())) {
            is_duplicate = true;
            bindingResult.rejectValue("userNickname", "signup.userNickname.duplicate", "이미 사용 중인 이메일입니다.");
        }

        if(!is_duplicate) {
            makeUserBirth(user);
            //userRepository.save(user);
            log.info("회원가입 성공: user={}", user);
        }
    }

    private static void makeUserBirth(User user) {
        // @ModelAttribute에 의해 User 객체의 기본 필드들이 바인딩된 후, userBirth를 설정합니다.
        if (user.getYear() != null && user.getMonth() != null && user.getDay() != null) {
            user.setUserBirth(LocalDate.of(user.getYear(), user.getMonth(), user.getDay()));
            System.out.println("hlllllllo");
        }
    }
}
