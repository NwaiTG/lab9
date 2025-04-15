package com.nwai.dentalsys;

import com.nwai.dentalsys.dto.request.AppointmentRequestDto;
import com.nwai.dentalsys.dto.response.AppointmentResponseDto;
import com.nwai.dentalsys.service.AppointmentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class DentalsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DentalsysApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(AppointmentService appointmentService) {
        return args -> {
            AppointmentRequestDto dto = new AppointmentRequestDto(
                    LocalDate.now(),
                    LocalTime.of(9, 0),
                    "Scheduled",
                    "Pending",
                    1, // patientId
                    1, // dentistId
                    1  // surgeryId
            );

            try {
                var response = appointmentService.createAppointment(dto);
                System.out.println("Appointment saved: " + response);
            } catch (Exception e) {
                System.out.println("Failed to save appointment: " + e.getMessage());
            }

            // 2. Retrieve and print all appointments
            List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
            System.out.println("All Appointments:");
            appointments.forEach(System.out::println);
        };
    }
}
