package com.nwai.dentalsys.mapper;

import java.util.List;

import com.nwai.dentalsys.dto.request.DentistRequestDto;
import com.nwai.dentalsys.dto.response.DentistResponseDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.nwai.dentalsys.model.Dentist;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AppointmentMapper.class)
public interface DentistMapper {

    @Mapping(source = "appointmentRequestDtoList", target = "appointmentList")
    Dentist dentistRequestDtoToDentist(DentistRequestDto dentistRequestDto);

    @Mapping(source = "appointmentList", target = "appointmentRequestDtoList")
    DentistRequestDto dentistToDentistRequestDto(Dentist dentist);

    @Mapping(source = "appointmentList", target = "appointmentResponseDtoList")
    DentistResponseDto dentistToDentistResponseDto(Dentist dentist);

    @Mapping(source = "appointmentList", target = "appointmentResponseDtoList")
    List<DentistResponseDto> dentistToDentistResponseDtoList(List<Dentist> dentistList);

}
