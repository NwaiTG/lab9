package com.nwai.dentalsys.controller;

import com.nwai.dentalsys.dto.request.AppointmentRequestDto;
import com.nwai.dentalsys.dto.response.AppointmentResponseDto;
import com.nwai.dentalsys.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto appointmentResponseDto = appointmentService.createAppointment(appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDto>> getAllAppointments(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam String sortDirection,
            @RequestParam String sortBy
    ) {
        Page<AppointmentResponseDto> appointmentResponsePage = appointmentService.searchAppointment(page, pageSize, sortDirection, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponsePage);
    }

    @GetMapping("/by-surgery")
    public ResponseEntity<Page<AppointmentResponseDto>> getAllAppointmentsBySurgery(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam String sortDirection,
            @RequestParam String sortBy,
            @RequestParam String surgery
    ){
        Page<AppointmentResponseDto> appointmentResponsePage = appointmentService.searchAppointmentBySurgery(surgery, page, pageSize, sortDirection, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponsePage);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto,  @PathVariable int appointmentId) {
        AppointmentResponseDto appointmentResponseDto = appointmentService.updateAppointment(appointmentId, appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponseDto);
    }

}
