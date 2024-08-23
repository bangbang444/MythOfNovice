package novice.present.repository;

import novice.present.domain.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmitFormRepository {

    public ItemInfo save(ItemInfo itemInfo);

    public ItemInfo findById(String id);

    public List<ItemInfo> findAll();

    public void clearStore();


}
