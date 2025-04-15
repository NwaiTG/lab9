package com.nwai.dentalsys.mapper;

import org.mapstruct.*;

import com.nwai.dentalsys.model.Surgery;
import com.nwai.dentalsys.dto.request.SurgeryRequestDto;
import com.nwai.dentalsys.dto.response.SurgeryResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AddressMapper.class, AppointmentMapper.class})
public interface SurgeryMapper {
    @Mappings({
            @Mapping(source = "addressRequestDto", target = "address"),
            @Mapping(source = "appointmentRequestDtoList", target = "appointmentList")
    })
    Surgery patientRequestDtoToPatient(SurgeryRequestDto patientRequestDto);

    @Mappings({
            @Mapping(source = "address", target = "addressResponseDto"),
            @Mapping(source = "appointmentList", target = "appointmentResponseDtoList")
    })
    SurgeryResponseDto surgeryToSurgeryResponseDto(Surgery surgery);
}
