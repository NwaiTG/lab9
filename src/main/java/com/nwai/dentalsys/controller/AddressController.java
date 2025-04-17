package com.nwai.dentalsys.controller;

import com.nwai.dentalsys.dto.response.AddressResponseDto;
import com.nwai.dentalsys.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // 7. GET all addresses sorted by city
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponseDto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddressesSortedByCity());
    }
}
