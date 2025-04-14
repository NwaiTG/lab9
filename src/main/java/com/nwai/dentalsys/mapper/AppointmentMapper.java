package com.nwai.dentalsys.mapper;

import org.mapstruct.*;
import com.nwai.dentalsys.model.Appointment;
import com.nwai.dentalsys.dto.request.AddressRequestDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    Appointment appointmentRequestDtoToAppointment(AddressRequestDto addressRequestDto);



}
