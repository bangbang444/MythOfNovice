package novice.present.repository;

import novice.present.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();

    private static long sequence = 0L;

    public User save(User user){
        user.setUserId(++sequence);
        store.put(user.getUserId(), user);
        return user;
    }

    public User findById(Long id){
        return store.get(id);
    }

    public void clearStore() {
        store.clear();
    }
}
