package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepositoryImpl;

    public User findByUserLoginId(String userLoginId) {
        // TODO: 구현해야 됨
        return null;
    }

    public User login(String loginId, String password, BindingResult bindingResult) {
        Optional<User> userOptional = userRepositoryImpl.findByUserLoginIdAndUserPassword(loginId, password);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            //bindingResult.reject();
            return null;
        }
    }
}
