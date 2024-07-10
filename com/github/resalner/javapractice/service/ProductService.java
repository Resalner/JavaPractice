package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;
import com.github.resalner.javapractice.request.ProductRequest;
import java.util.List;

@Service
public class ProductService{
  @Autowired
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository){
    this.productRepository = productRepository;
  }

  public List<Product> getProducts(){
    return productRepository.findAll();
  }

  public void addProduct(ProductRequest productRequest){
    Product product = new Product();
    product.setName(productRequest.name());
    product.setDescription(productRequest.description());
    product.setPrice(productRequest.price());
    product.setCategory_ID(productRequest.category_ID());
    productRepository.save(product);
  }

  public Product getProduct(long id){
    return productRepository.findById(id).get();
  }

  public void deleteProduct(long id){
    productRepository.deleteById(id);
  }
  public Product updateProduct(long id, ProductRequest productRequest){
    Product pr = productRepository.findById(id).get();
    if(Objects.nonNull(productRequest.name()) && !"".equals(productRequest.name())){
      pr.setName(productRequest.name());
    }
    if(Objects.nonNull(productRequest.description()) && !"".equals(productRequest.description())){
      pr.setDescription(productRequest.description());
    }
    if(Objects.nonNull(productRequest.price()) && !"".equals(productRequest.price())){
      pr.setPrice(productRequest.price());
    }
    if(Objects.nonNull(productRequest.category_ID()) && !"".equals(productRequest.category_ID())){
      pr.setCategory_ID(productRequest.category_ID());
    }
    productRepository.save(pr);
  }
}