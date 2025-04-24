package com.example.baitapbuoi2.service.impl;

import com.example.baitapbuoi2.dto.AddressDTO;
import com.example.baitapbuoi2.entity.Address;
import com.example.baitapbuoi2.entity.User;
import com.example.baitapbuoi2.exception.UserNotFoundException;
import com.example.baitapbuoi2.repository.AddressRepository;
import com.example.baitapbuoi2.repository.UserRepository;
import com.example.baitapbuoi2.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO) {
        User user = userRepository.findById(addressDTO.getUserId())
                                 .orElseThrow(() -> new UserNotFoundException(addressDTO.getUserId()));

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return convertToAddressDTO(savedAddress);
    }

    private AddressDTO convertToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setUserId(address.getUser().getId());
        return addressDTO;
    }
}