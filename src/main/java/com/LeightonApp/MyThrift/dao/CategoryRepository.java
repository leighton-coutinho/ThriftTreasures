package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// entity and integer primary key type
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
}
