package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.dao.StoreRepository;
import com.LeightonApp.MyThrift.dao.UserRepository;
import com.LeightonApp.MyThrift.entity.Store;
import com.LeightonApp.MyThrift.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository theStoreRepository) {
        storeRepository = theStoreRepository;
    }

    @Override
    public List<Store> findAll() {
            return storeRepository.findAll();
    }

    @Override
    public Optional<Store> findById(int userID) {
        return storeRepository.findById(userID);
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void deleteById(int userID) {
        storeRepository.deleteById(userID);
    }

    @Override
    public Optional<Store> authenticateUser(String username, String password) {
        return storeRepository.authenticateUser(username, password);
    }

    @Override
    public Optional<Store> findUser(String username) {
        return storeRepository.findUser(username);
    }
}


