package com.nwai.dentalsys.repository;

import com.nwai.dentalsys.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Only one custom finder as requested
    List<Appointment> findByStatus(String status);
    Page<Appointment> findBySurgery_surgeryName(String surgery, Pageable pageable);
}
