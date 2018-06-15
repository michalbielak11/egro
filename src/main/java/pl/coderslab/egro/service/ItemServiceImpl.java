package pl.coderslab.egro.service;

import org.springframework.stereotype.Service;
import pl.coderslab.egro.entity.Item;
import pl.coderslab.egro.repository.ItemRepository;
@Service
public class ItemServiceImpl implements ItemService
{
    private final ItemRepository itemRepository;


    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveItem(Item item) {
        item.setQuantity(1);
        itemRepository.save(item);
    }
}
