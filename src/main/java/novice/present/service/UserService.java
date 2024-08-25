package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User login(String loginId, String password, BindingResult bindingResult) {
        Optional<User> userOptional = userRepository.findByUserLoginIdAndUserPassword(loginId, password);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            bindingResult.reject("loginUserNotExist", null, null);
            return null;
        }
    }
}
