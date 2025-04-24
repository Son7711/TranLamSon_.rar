package com.example.baitapbuoi2.controller;

import com.example.baitapbuoi2.dto.AddressDTO;
import com.example.baitapbuoi2.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO savedAddressDTO = addressService.addAddress(addressDTO);
        return ResponseEntity.ok(savedAddressDTO);
    }
}