package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.request.CategoryRequest;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
    }

    public Category updateCategory(long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
        if (Objects.nonNull(categoryRequest.name())
                && !"".equals(categoryRequest.name())) {

            category.setName(categoryRequest.name());
        }
        categoryRepository.save(category);
    }
}