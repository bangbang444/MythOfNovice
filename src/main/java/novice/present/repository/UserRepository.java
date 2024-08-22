package novice.present.repository;

import novice.present.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@Repository
public interface UserRepository {
    public User save(User user);

    public User findById(Long id);

    public void clearStore();

    Optional<User> findByUserLoginIdAndUserPassword(String userLoginId, String userPassword);
}
