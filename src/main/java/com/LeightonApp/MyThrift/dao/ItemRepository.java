package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Item;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// entity and integer primary key type
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByStore(Store store);

    @Query("SELECT i FROM Item i WHERE i.name = :itemname")
    Optional<Item> findByName(@Param("itemname") String itemname);
}
