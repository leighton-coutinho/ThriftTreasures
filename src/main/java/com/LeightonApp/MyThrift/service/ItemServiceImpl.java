package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.dao.ItemRepository;
import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository) {
        itemRepository = theItemRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(int id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findByStore(Store store) {
        return itemRepository.findByStore(store);
    }
}
