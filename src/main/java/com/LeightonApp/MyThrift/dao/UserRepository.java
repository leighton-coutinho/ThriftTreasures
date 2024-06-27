package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
// entity and integer primary key type

public interface UserRepository extends JpaRepository<User, Integer> {
}
