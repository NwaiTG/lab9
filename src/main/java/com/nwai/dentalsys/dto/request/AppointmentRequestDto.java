package com.nwai.dentalsys.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRequestDto(
   LocalDate appointmentDate,
   LocalTime appointmentTime,
   String status,
   String confirmStatus,
   int patientId,
   int dentistId,
   int surgeryId
) {
}
