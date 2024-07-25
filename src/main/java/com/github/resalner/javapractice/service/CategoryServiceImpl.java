package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

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
		return categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найдена категория с id = " + id));
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category updateCategory(long id, Category categoryForUpdate) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найдена категория с id = " + id));
		String newName = categoryForUpdate.getName();
		if (Objects.nonNull(newName) && !"".equals(newName)) {

			category.setName(newName);
		}
		category = categoryRepository.save(category);
		return category;
	}
}