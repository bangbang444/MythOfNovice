package novice.present.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novice.present.domain.ItemInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SubmitFormRepositoryImpl implements SubmitFormRepository{

    private static final Map<String, ItemInfo> itemInfoStore = new ConcurrentHashMap<>();


    @Override
    public ItemInfo save(ItemInfo itemInfo) {
        String itemId = UUID.randomUUID().toString();
        itemInfo.setId(itemId);
        itemInfoStore.put(itemId, itemInfo);
        return itemInfo;
    }

    @Override
    public ItemInfo findById(String id) {
        return null;
    }

    @Override
    public List<ItemInfo> findAll() {
        return List.of();
    }

    @Override
    public void clearStore() {

    }
}
