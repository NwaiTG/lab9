package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.AppointmentRequestDto;
import com.nwai.dentalsys.dto.response.AppointmentResponseDto;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);

    AppointmentResponseDto updateAppointment(int id, AppointmentRequestDto dto);

    void deleteAppointment(int id);

    Optional<AppointmentResponseDto> getAppointmentById(int id);

    List<AppointmentResponseDto> getAllAppointments();
}
