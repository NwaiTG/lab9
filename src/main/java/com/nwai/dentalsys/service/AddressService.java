package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.response.AddressResponseDto;

import java.util.List;

public interface AddressService {
    List<AddressResponseDto> getAllAddressesSortedByCity();
}
