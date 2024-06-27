package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findByName(String name);
    Category save(Category category);
    void deleteByName(String name);

    Category findOrAddCategory(String name);
}
