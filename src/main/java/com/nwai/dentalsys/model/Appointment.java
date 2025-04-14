package com.nwai.dentalsys.model;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private int id;
    @Column(nullable = false)
    private LocalDate appointmentDate;
    @Column(nullable = false)
    private LocalTime appointmentTime;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String confirmStatus;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "sugery_id")
    private Sugery sugery;

    public Appointment(LocalDate appointmentDate, LocalTime appointmentTime, String status, String confirmStatus, Patient patient, Dentist dentist, Sugery sugery) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.confirmStatus = confirmStatus;
        this.patient = patient;
        this.dentist = dentist;
        this.sugery = sugery;
    }
}
