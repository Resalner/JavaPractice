package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;
import com.github.resalner.javapractice.request.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implement ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
    }

    public Product updateProduct(long id, Product product) {
        Product pr = productRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
        if (Objects.nonNull(product.name())
                && !"".equals(product.name())) {

            pr.setName(product.name());
        }
        if (Objects.nonNull(product.description())
                && !"".equals(product.description())) {

            pr.setDescription(product.description());
        }
        if (Objects.nonNull(product.price())
                && !"".equals(product.price())) {

            pr.setPrice(product.price());
        }
        if (Objects.nonNull(product.categoryId())
                && !"".equals(product.categoryId())) {

            pr.setcategoryId(product.categoryId());
        }
        productRepository.save(pr);
        return pr;
    }
}