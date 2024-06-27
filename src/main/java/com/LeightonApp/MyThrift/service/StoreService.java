package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.entity.User;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> findAll();
    Optional<Store> findById(int userID);
    Store save(Store store);
    void deleteById(int userID);
    Optional<Store> authenticateUser(String username, String password);

    Optional<Store> findUser(String username);

}
