package com.nwai.dentalsys.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sugery")
@NoArgsConstructor
@Data
public class Sugery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sugery_id")
    private int id;
    @Column(nullable = false)
    private String sugeryNo;
    @Column(nullable = false)
    private String sugeryName;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "sugery")
    private List<Appointment> appointmentList;

    public Sugery(String sugeryNo, String sugeryName){
        this.sugeryNo = sugeryNo;
        this.sugeryName = sugeryName;
    }

}
