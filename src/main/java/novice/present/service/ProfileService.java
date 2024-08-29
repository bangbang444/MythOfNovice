package novice.present.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.User;
import novice.present.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;



//    public User updateUserProfile(String userLoginId, String newNickname, String newJob, String newEmail, int year, int month, int day) {
//        // 사용자 조회
//        Optional<User> optionalUser = userRepository.findByUserLoginId(userLoginId);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            // 사용자 정보 수정
//            user.setUserNickname(newNickname);
//            user.setUserEmail(newEmail);
//            user.setUserJob(newJob);
//            user.setUserBirth(LocalDate.of(year, month, day));
//
//            // 사용자 정보 저장
//            return userRepository.save(user);
//        }
//        return null;
//    }
}
