package com.nwai.dentalsys.service;

import com.nwai.dentalsys.dto.request.AppointmentRequestDto;
import com.nwai.dentalsys.dto.response.AppointmentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    Page<AppointmentResponseDto> searchAppointment(int page, int pageSize, String sortDirection, String sortField);

    Page<AppointmentResponseDto> searchAppointmentBySurgery(String surgery, int page, int pageSize, String sortDirection, String sortField);

    AppointmentResponseDto createAppointment(AppointmentRequestDto dto);

    AppointmentResponseDto updateAppointment(int id, AppointmentRequestDto dto);

    void deleteAppointment(int id);

    Optional<AppointmentResponseDto> getAppointmentById(int id);

    List<AppointmentResponseDto> getAllAppointments();
}
