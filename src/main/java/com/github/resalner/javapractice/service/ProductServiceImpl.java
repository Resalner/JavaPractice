package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		product = productRepository.save(product);
		return product;
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден продукт с id = " + id));
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(long id, Product productForUpdate) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден продукт с id = " + id));

		String name = productForUpdate.getName();
		String description = productForUpdate.getDescription();
		double price = productForUpdate.getPrice();
		Category category = productForUpdate.getCategory();

		if (Objects.nonNull(name) && !"".equals(name)) {

			product.setName(name);
		}
		if (Objects.nonNull(description) && !"".equals(description)) {

			product.setDescription(description);
		}
		if (Objects.nonNull(price)) {

			product.setPrice(price);
		}
		if (Objects.nonNull(category)) {

			product.setCategory(category);
		}
		product = productRepository.save(product);
		return product;
	}
}