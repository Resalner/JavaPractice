package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.request.ProductRequest;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getProducts();

    Product getProduct(long id);

    void addProduct(ProductRequest productRequest);

    void deleteProduct(long id);

    Product updateProduct(long id, ProductRequest productRequest);
}