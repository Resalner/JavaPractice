package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.request.AddressRequest;
import java.util.List;

@Service
public class AddressServiceImpl{
  @Autowired
  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository){
    this.addressRepository = addressRepository;
  }

  public List<Address> getAddresses(){
    return addressRepository.findAll();
  }

  public void addAddress(AddressRequest addressRequest){
    Address address = new Address();
    address.setCity(addressRequest.city());
    address.setStreet(addressRequest.street());
    address.setHouse_number(addressRequest.house_number());
    address.setApartament_number(addressRequest.apartament_number());
    addressRepository.save(address);
  }

  public Address getAddress(long id){
    return addressRepository.findById(id).orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
  }
  
  public void deleteAddress(long id){
    addressRepository.deleteById(id).orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
  }
  
  public Address updateAddress(long id, AddressRequest addressRequest){
    Address ad = addressRepository.findById(id).orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
    if(Objects.nonNull(addressRequest.city()) && !"".equals(addressRequest.city())){
      ad.setCity(addressRequest.city());
    }
    if(Objects.nonNull(addressRequest.street()) && !"".equals(addressRequest.street())){
      ad.setStreet(addressRequest.street());
    }
    if(Objects.nonNull(addressRequest.house_number()) && !"".equals(addressRequest.house_number())){
      ad.setHouse_number(addressRequest.house_number());
    }
    if(Objects.nonNull(addressRequest.apartament_number()) && !"".equals(addressRequest.apartament_number())){
      ad.setApartament_number(addressRequest.apartament_number());
    }
    addressRepository.save(ad);
  }
}