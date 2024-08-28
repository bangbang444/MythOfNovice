package novice.present.repository;

import novice.present.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserLoginIdAndUserPassword(String loginId, String password);

    boolean existsByUserLoginId(String userLoginId);
}
