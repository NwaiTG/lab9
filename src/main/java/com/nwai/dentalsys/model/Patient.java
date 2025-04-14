package com.nwai.dentalsys.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private int id;
    @Column(nullable = false)
    private String patNo;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private java.time.LocalDate dob;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private Integer email;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;

    public Patient(String patNo, String firstName, String lastName, java.time.LocalDate dob, String phoneNumber, Integer email) {
        this.patNo = patNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
