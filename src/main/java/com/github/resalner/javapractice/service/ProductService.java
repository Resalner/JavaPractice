package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(long id);

    Product saveProduct(Product product);

    void deleteProduct(long id);

    Product updateProduct(long id, Product product);
}