package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.dao.CategoryRepository;
import com.LeightonApp.MyThrift.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository theCategoryRepository) {
        categoryRepository = theCategoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findById(name);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteByName(String name) {
        categoryRepository.deleteById(name);
    }

    @Override
    public Category findOrAddCategory(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            category = new Category(name);
            categoryRepository.save(category);
        }
        return category;
    }
}
