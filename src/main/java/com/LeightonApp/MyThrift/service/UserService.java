package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(int userID);
    User save(User user);
    void deleteById(int userID);
}
