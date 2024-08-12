package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		category = categoryRepository.save(category);
		return category;
	}

	@Override
	public Category getCategory(long id) {
		return getCategoryIfExists(id);
	}

	@Override
	public void deleteCategory(long id) {
		if (!categoryRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public Category updateCategory(long id, Category categoryForUpdate) {
		Category category = getCategoryIfExists(id);
		
		String newName = categoryForUpdate.getName();
		
		category.setName(newName);
		
		category = categoryRepository.save(category);
		
		return category;
	}

	private Category getCategoryIfExists(long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найдена категория с id = " + id));
	}
}