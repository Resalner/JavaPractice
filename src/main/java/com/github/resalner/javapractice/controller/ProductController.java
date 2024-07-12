package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;
import com.github.resalner.javapractice.request.ProductRequest;
import com.github.resalner.javapractice.service.ProductService;
import com.github.resalner.javapractice.dto.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products" )
@RequiredArgsConstructor
public class ProductController{
  @Autowired
  private final ProductService productService;
 
  @GetMapping
  public List<ProductResponse> getProducts(){
    return productService.getProducts();
  } 

  @PostMapping
  public ProductResponse saveProduct(@RequestBody @Valid ProductRequest productRequest){
    productService.addProduct(productRequest);
  }
  
  @GetMapping("/{id}")
  public ProductResponse getProduct(@PathVariable("id") long productid){
    return productService.getProduct(productid);
  }
  
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable("id") long productid){
    productService.deleteProduct(productid);
  }
  
  @PutMapping("/{id}")
  public ProductResponse updateProduct(@PathVariable("id") long productid, @RequestBody @Valid ProductRequest productRequest){
    return productService.updateProduct(productid, productRequest);
  }
}