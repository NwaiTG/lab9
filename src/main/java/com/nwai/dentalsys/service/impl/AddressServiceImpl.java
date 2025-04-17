package com.nwai.dentalsys.service.impl;

import com.nwai.dentalsys.dto.response.AddressResponseDto;
import com.nwai.dentalsys.mapper.AddressMapper;
import com.nwai.dentalsys.repository.AddressRepository;
import com.nwai.dentalsys.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressResponseDto> getAllAddressesSortedByCity() {
        return addressRepository.findAllByOrderByCityAsc()
                .stream()
                .map(addressMapper::addressToAddressResponseDto)
                .collect(Collectors.toList());
    }
}
