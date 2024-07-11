package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.request.CategoryRequest;

import com.github.resalner.javapractice.service.CategoryService;
import java.util.List;

@RestController
@RequestMapping(path = "/api/categories" )
public class CategoryController{
  private final CategoryService categoryService;
  public CategoryController(CategoryService categoryService){
    this.categoryService = categoryService;
  }
// получение списка категорий
  @GetMapping
  public List<Category> getCategories(){
    return categoryService.getCategories();
  } 
// добавление новой категории
  @PostMapping("/addCategory")
  public void saveCategory(@RequestBody @Valid CategoryRequest categoryRequest){
    categoryService.addCategory(categoryRequest);
  }
  @GetMapping("/getCategory/{id}")
  public Category getCategory(@PathVariable("id") long categoryId){
    return categoryService.getCategory(categoryId);
  }
  @DeleteMapping("/deleteCategory/{id}")
  public void deleteCategory(@PathVariable("id") long categoryId){
    categoryService.deleteCategory(categoryId);
  }
  @PostMapping("/updateCategory/{id}")
  public Category updateCategory(@PathVariable("id") long categoryId, @RequestBody @Valid CategoryRequest categoryRequest){
    return categoryService.updateCategory(categoryId, categoryRequest);
  }
}