package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.request.AddressRequest;
import java.util.List;

public interface AddressService {
  List<Address> getAddresses();
  Address getAddress(long id);
  void addAddress(AddressRequest addressRequest);
  Address updateAddress(long id, AddressRequest addressRequest);
  void deleteAddress(long id);
  
}