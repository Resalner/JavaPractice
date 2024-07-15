package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;
import com.github.resalner.javapractice.request.ProductRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setCategoryId(productRequest.categoryId());
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

    public Product updateProduct(long id, ProductRequest productRequest) {
        Product pr = productRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден продукт с id = " + id));
        if (Objects.nonNull(productRequest.name())
                && !"".equals(productRequest.name())) {

            pr.setName(productRequest.name());

        }
        if (Objects.nonNull(productRequest.description())
                && !"".equals(productRequest.description())) {

            pr.setDescription(productRequest.description());

        }
        if (Objects.nonNull(productRequest.price())
                && !"".equals(productRequest.price())) {

            pr.setPrice(productRequest.price());

        }
        if (Objects.nonNull(productRequest.categoryId())
                && !"".equals(productRequest.categoryId())) {

            pr.setcategoryId(productRequest.categoryId());

        }

        productRepository.save(pr);
    }
}