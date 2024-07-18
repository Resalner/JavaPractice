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

    @Ovveride
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Ovveride
    public Category saveCategory(Category category) {
        category = categoryRepository.save(category);
        return category;
    }

    @Ovveride
    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
    }

    @Ovveride
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
    }

    @Ovveride
    public Category updateCategory(long id, Category categoryForUpdate) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найдена категория с id = " + id));
        if (Objects.nonNull(categoryForUpdate.name())
                && !"".equals(categoryForUpdate.name())) {

            category.setName(categoryForUpdate.name());
        }
        categoryRepository.save(category);
        return category;
    }
}