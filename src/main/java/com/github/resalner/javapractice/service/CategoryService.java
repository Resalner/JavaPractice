package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.request.CategoryRequest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CategoryService{

  @Autowired
  private final CategoryRepository categoryRepository;
  

  public CategoryService(CategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategories(){
    return categoryrRepository.findAll();
  }

  public void addCategory(CategoryRequest categoryRequest){
    Cateogry category = new Category();
    category.setName(categoryRequest.name());
    categoryRepository.save(category);
    
  }
  public Category getCategory(long id){
    return categoryRepository.findById(id).get();
  }
  public void deleteCategory(long id){
    categoryRepository.deleteById(id);
  }
  public Category updateCategory(long id, CategoryRequest categoryRequest){
    Category category = categoryRepository.findById(id).get().orElseThrow(() -> new RuntimeException("Category not found"));
    
    if(Objects.nonNull(categoryRequest.name()) && !"".equals(categoryRequest.name())){
      category.setName(categoryRequest.name());
    }
    categoryRepository.save(category);
  }
}