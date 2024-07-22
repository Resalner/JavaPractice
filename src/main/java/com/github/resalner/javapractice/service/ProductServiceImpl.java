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
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
    }

    @Ovveride
    public Product updateProduct(long id, Product productForUpdate) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
        if (Objects.nonNull(productForUpdate.name())
                && !"".equals(productForUpdate.name())) {

            product.setName(productForUpdate.name());
        }
        if (Objects.nonNull(productForUpdate.description())
                && !"".equals(productForUpdate.description())) {

            product.setDescription(productForUpdate.description());
        }
        if (Objects.nonNull(productForUpdate.price())
                && !"".equals(productForUpdate.price())) {

            product.setPrice(productForUpdate.price());
        }
        if (Objects.nonNull(productForUpdate.categoryId())
                && !"".equals(productForUpdate.categoryId())) {

            product.setcategoryId(productForUpdate.categoryId());
        }
        product = productRepository.save(product);
        return product;
    }
}