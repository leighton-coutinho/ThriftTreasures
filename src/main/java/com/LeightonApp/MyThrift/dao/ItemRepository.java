package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// entity and integer primary key type
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByStore(Store store);
}
