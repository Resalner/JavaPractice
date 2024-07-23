package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.request.CategoryRequest;
import com.github.resalner.javapractice.service.CategoryService;
import com.github.resalner.javapractice.dto.CategoryResponse;
import com.github.resalner.javapractice.map.CategoryMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryMapper mapper;
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return mapper.toDomain(categoties);
    }

    @PostMapping
    public CategoryResponse saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        Category category = mapper.toCategory(categoryRequest);
        categoryService.saveCategory(category);
        return mapper.toResponse(category);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable("id") long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable("id") long categoryId, @RequestBody @Valid CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = mapper.toDomain(categoryRequest);
        Category category = mapper.toCategory(categoryResponse);
        return categoryService.updateCategory(categoryId, category);

    }
}