package com.nwai.dentalsys.service.impl;

import com.nwai.dentalsys.dto.request.AppointmentRequestDto;
import com.nwai.dentalsys.dto.response.AppointmentResponseDto;
import com.nwai.dentalsys.mapper.AppointmentMapper;
import com.nwai.dentalsys.model.Appointment;
import com.nwai.dentalsys.model.Patient;
import com.nwai.dentalsys.model.Dentist;
import com.nwai.dentalsys.model.Surgery;
import com.nwai.dentalsys.repository.AppointmentRepository;
import com.nwai.dentalsys.repository.PatientRepository;
import com.nwai.dentalsys.repository.DentistRepository;
import com.nwai.dentalsys.repository.SurgeryRepository;
import com.nwai.dentalsys.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final SurgeryRepository surgeryRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientRepository patientRepository,
                                  DentistRepository dentistRepository,
                                  SurgeryRepository surgeryRepository,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.surgeryRepository = surgeryRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        Appointment appointment = appointmentMapper.appointmentRequestDtoToAppointment(dto);

        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Dentist dentist = dentistRepository.findById(dto.dentistId())
                .orElseThrow(() -> new RuntimeException("Dentist not found"));
        Surgery surgery = surgeryRepository.findById(dto.surgeryId())
                .orElseThrow(() -> new RuntimeException("Surgery not found"));

        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);

        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.appointmentToAppointmentResponseDto(saved);
    }

    @Override
    public AppointmentResponseDto updateAppointment(int id, AppointmentRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentDate(dto.appointmentDate());
        appointment.setAppointmentTime(dto.appointmentTime());
        appointment.setStatus(dto.status());
        appointment.setConfirmStatus(dto.confirmStatus());

        appointment.setPatient(patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        appointment.setDentist(dentistRepository.findById(dto.dentistId())
                .orElseThrow(() -> new RuntimeException("Dentist not found")));
        appointment.setSurgery(surgeryRepository.findById(dto.surgeryId())
                .orElseThrow(() -> new RuntimeException("Surgery not found")));

        return appointmentMapper.appointmentToAppointmentResponseDto(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Optional<AppointmentResponseDto> getAppointmentById(int id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::appointmentToAppointmentResponseDto);
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::appointmentToAppointmentResponseDto)
                .collect(Collectors.toList());
    }
}
