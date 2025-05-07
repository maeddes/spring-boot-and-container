package io.nvtc.tc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    private final ItemRepository itemRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    public Optional<Item> getItemById(Integer id) {
        return itemRepository.findById(id);
    }
    
    public Item createOrUpdateItem(Item newItem) {
        Optional<Item> existingItem = itemRepository.findByName(newItem.getName());
        
        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.increaseQuantity(newItem.getQuantity());
            return itemRepository.save(item);
        } else {
            return itemRepository.save(newItem);
        }
    }
    
    public Optional<Item> updateItem(Integer id, Item itemDetails) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(itemDetails.getName());
                    item.setQuantity(itemDetails.getQuantity());
                    return itemRepository.save(item);
                });
    }
    
    public boolean deleteItem(Integer id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return true;
                }).orElse(false);
    }
}