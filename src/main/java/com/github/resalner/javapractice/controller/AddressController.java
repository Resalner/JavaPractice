package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.request.AddressRequest;
import com.github.resalner.javapractice.service.AddressService;
import com.github.resalner.javapractice.dto.AddressResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import java.util.List;
 
@RestController
@RequestMapping(path = "/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController{
  
  private final AddressService addressService;

  @GetMapping
  public List<AddressResponse> getAddresses(){
    return addressService.getAddresses();
  } 
  
  @PostMapping
  public AddressResponse saveAddress(@RequestBody @Valid AddressRequest addressRequest){
    addressService.addAddress(addressRequest);
  }
  
  @GetMapping("/{id}")
  public AddressResponse getAddress(@PathVariable("id") long addressid){
    return addressService.getAddress(addressid);
  }
  
  @DeleteMapping("/{id}")
  public void deleteAddress(@PathVariable("id") long addressid){
    addressService.deleteAddress(addressid);
  }
  
  @PutMapping("/{id}")
  public AddressResponse updateAddress(@PathVariable("id") long addressid, @RequestBody @Valid AddressRequest addressRequest){
    return addressService.updateAddress(addressid, addressRequest);
  }
}