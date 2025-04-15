package com.nwai.dentalsys.dto.response;

import com.nwai.dentalsys.model.*;
import java.util.List;

public record SurgeryResponseDto(
        String sugeryNo,
        String sugeryName,
        AddressResponseDto addressResponseDto,
        List<AppointmentResponseDto> appointmentResponseDtoList
) {
}
