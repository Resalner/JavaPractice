package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category getCategory(long id);

    Category saveCategory(Category category);

    void deleteCategory(long id);

    Category updateCategory(long id, Category category);
}