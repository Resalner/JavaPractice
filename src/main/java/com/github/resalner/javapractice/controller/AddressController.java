package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.request.AddressRequest;

import com.github.resalner.javapractice.service.AddressService;
import java.util.List;
 
@RestController
@RequestMapping(path = "/api/addresses" )
public class AddressController{
  private final AddressService addressService;
  public AddressController(AddressService addressService){
    this.addressService = addressService;
  }
// получение списка адресов
  @GetMapping
  public List<Address> getAddresses(){
    return addressService.getAddresses();
  } 
// добавление нового адреса
  @PostMapping("/addAddress")
  public void saveAddress(@RequestBody @Valid AddressRequest addressRequest){
    addressService.addAddress(addressRequest);
  }
  @GetMapping("/getAddress/{id}")
  public Address getAddress(@PathVariable("id") long addressid){
    return addressService.getAddress(addressid);
  }
  @DeleteMapping("/deleteAddress/{id}")
  public void deleteAddress(@PathVariable("id") long addressid){
    addressService.deleteAddress(addressid);
  }
  @PostMapping("/updateAddress/{id}")
  public Address updateAddress(@PathVariable("id") long addressid, @RequestBody @Valid AddressRequest addressRequest){
    return addressService.updateAddress(addressid, addressRequest);
  }
}