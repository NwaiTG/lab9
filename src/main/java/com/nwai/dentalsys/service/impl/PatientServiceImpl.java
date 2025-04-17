package com.nwai.dentalsys.service.impl;

import com.nwai.dentalsys.dto.request.AddressRequestDto;
import com.nwai.dentalsys.dto.request.PatientRequestDto;
import com.nwai.dentalsys.dto.response.PatientResponseDto;
import com.nwai.dentalsys.mapper.AddressMapper;
import com.nwai.dentalsys.mapper.PatientMapper;
import com.nwai.dentalsys.model.Address;
import com.nwai.dentalsys.model.Patient;
import com.nwai.dentalsys.repository.PatientRepository;
import com.nwai.dentalsys.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AddressMapper addressMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, AddressMapper addressMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<PatientResponseDto> getAllPatientsSortedByLastName() {
        return patientRepository.findAllByOrderByLastNameAsc()
                .stream()
                .map(patientMapper::patientToPatientResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientResponseDto> getPatientById(int id) {
        return patientRepository.findById(id)
                .map(patientMapper::patientToPatientResponseDto);
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        Patient patient = patientMapper.patientRequestDtoToPatient(dto);
        return patientMapper.patientToPatientResponseDto(patientRepository.save(patient));
    }

    @Override
    public Optional<PatientResponseDto> updatePatient(int id, PatientRequestDto dto) {
        return patientRepository.findById(id).map(existing -> {
            // Update patient fields
            existing.setPatNo(dto.patNo());
            existing.setFirstName(dto.firstName());
            existing.setLastName(dto.lastName());
            existing.setDob(dto.dob());
            existing.setPhoneNumber(dto.phoneNumber());
            existing.setEmail(dto.email());

            // Handle address
            AddressRequestDto addressDto = dto.addressRequestDto();

            if (addressDto != null) {
                if (existing.getAddress() != null) {
                    // Update existing address
                    Address existingAddress = existing.getAddress();
                    existingAddress.setCity(addressDto.city());
                    existingAddress.setStreet(addressDto.street());
                    existingAddress.setState(addressDto.state());
                    existingAddress.setUnitNo(addressDto.unitNo());
                    existingAddress.setZip(addressDto.zip());
                } else {
                    // Set new address if none exists
                    existing.setAddress(addressMapper.addressRequestDtoToAddress(addressDto));
                }
            }

            // Save and return response DTO
            Patient saved = patientRepository.save(existing);
            return patientMapper.patientToPatientResponseDto(saved);
        });
    }

    @Override
    public boolean deletePatientById(int id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PatientResponseDto> searchPatients(String searchString) {
        return patientRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchString, searchString)
                .stream()
                .map(patientMapper::patientToPatientResponseDto)
                .collect(Collectors.toList());
    }
}
