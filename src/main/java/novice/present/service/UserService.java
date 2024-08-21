package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUserLoginId(String userLoginId) {
        // TODO: 구현해야 됨
        return null;
    }

    public User login(String loginId, String password) {
        // TODO: 구현해야 됨
        return null;
    }
}
