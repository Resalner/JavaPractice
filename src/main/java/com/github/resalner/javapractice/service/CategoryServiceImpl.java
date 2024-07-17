package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
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

    public Category updateCategory(long id, Category category) {
        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
        if (Objects.nonNull(category.name())
                && !"".equals(category.name())) {

            cat.setName(category.name());
        }
        categoryRepository.save(cat);
        return cat;
    }
}