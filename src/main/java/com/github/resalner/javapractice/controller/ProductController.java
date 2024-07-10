package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.repository.ProductRepository;
import com.github.resalner.javapractice.request.ProductRequest;

import com.github.resalner.javapractice.service.ProductService;
import java.util.List;

@RestController
@RequestMapping(path = "/api/products" )
public class ProductController{
  private final ProductService productService;
  public ProductController(ProductService productService){
    this.productService = productService;
  }
// получение списка продуктов
  @GetMapping
  public List<Product> getProducts(){
    return productService.getProducts();
  } 
// добавление нового продукта
  @PostMapping("/addProduct")
  public void saveProduct(@RequestBody @Valid ProductRequest productRequest){
    productService.addProduct(productRequest);
  }
  @GetMapping("/getProduct/{id}")
  public Product getProduct(@PathVariable("id") long productid){
    return productService.getProduct(productid);
  }
  @DeleteMapping("/deleteProduct/{id}")
  public void deleteProduct(@PathVariable("id") long productid){
    productService.deleteProduct(productid);
  }
  @PostMapping("/updateProduct/{id}")
  public Product updateProduct(@PathVariable("id") long productid, @RequestBody @Valid ProductRequest productRequest){
    return productService.updateProduct(productid, productRequest);
  }
}