package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.CategoryRepository;
import com.github.resalner.javapractice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		Category category = product.getCategory();
		if (category != null && category.getId() != null) {
			categoryRepository.findById(category.getId()).orElseThrow(
					() -> new EntityNotFoundException("Категория с id = " + category.getId() + " не найдена"));
		}

		return productRepository.save(product);
	}

	@Override
	public Product getProduct(long id) {
		return getProductIfExists(id);
	}

	@Override
	public void deleteProduct(long id) {
		if (!productRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}

		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(long id, Product productForUpdate) {
		Product product = getProductIfExists(id);

		String name = productForUpdate.getName();
		String description = productForUpdate.getDescription();
		double price = productForUpdate.getPrice();
		Category category = productForUpdate.getCategory();

		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setCategory(category);
		
		product = productRepository.save(product);
		
		return product;
	}

	private Product getProductIfExists(long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден продукт с id = " + id));
	}
}