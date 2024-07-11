package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.request.CategoryRequest;
import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getCategories();
    Category getCategory(long id);
    void addCategory(CategoryRequest categoryRequest);
    void deleteCategory(long id);
    Category updateCategory(long id, CategoryRequest categoryRequest);
}