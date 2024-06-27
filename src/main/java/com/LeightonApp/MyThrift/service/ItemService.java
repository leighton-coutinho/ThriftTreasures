package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Store;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findAll();
    Optional<Item> findById(int id);
    Item save(Item item);
    void deleteById(int id);
    List<Item> findByStore(Store store);
}
