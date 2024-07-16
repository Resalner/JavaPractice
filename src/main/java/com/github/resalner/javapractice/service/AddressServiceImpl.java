package com.github.resalner.javapractice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.map.AddressMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
  
  private final AddressRepository addressRepository;

  public List<Address> getAddresses(){
    return addressRepository.findAll();
  }

  public void saveAddress(Address address){    
    addressRepository.save(address);
  }

  public Address getAddress(long id){
    return addressRepository.findById(id)
      .orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
  }
  
  public void deleteAddress(long id){
    addressRepository.deleteById(id)
      .orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
  } 
  
  public Address updateAddress(long id, Address address){
    Address ad = addressRepository.findById(id)
      .orElseThrow(()->EntityNotFoundException("не найден адрес с id = "+id));
    if(Objects.nonNull(address.city())
       && !"".equals(address.city())){
      
      ad.setCity(address.city());
    }
    if(Objects.nonNull(address.street())
       && !"".equals(address.street())){
      
      ad.setStreet(address.street());
    }
    if(Objects.nonNull(address.houseNumber()) 
       && !"".equals(address.houseNumber())){
      
      ad.setHouseNumber(address.houseNumber());
    }
    if(Objects.nonNull(address.apartamentNumber())
       && !"".equals(address.apartamentNumber())){
      
      ad.setApartamentNumber(address.apartamentNumber());
    }
    addressRepository.save(ad);
    return
  }
}